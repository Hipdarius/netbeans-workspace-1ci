package h8_spellchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Common word loading and analysis for the three dictionary types. */
public abstract class SpellChecker {
    private static final Pattern WORD = Pattern.compile("\\p{L}[\\p{L}\\p{M}]*");
    public abstract ArrayList<Error> analyseText(String text);

    protected void loadWords(Collection<String> words) throws IOException {
        Path file = Path.of("DE_Wortliste_UTF8.txt");
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = normalize(line.replace("\uFEFF", "").trim());
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        }
    }

    private String normalize(String word) {
        return Normalizer.normalize(word, Normalizer.Form.NFC).toLowerCase(Locale.GERMAN);
    }

    protected ArrayList<Error> findErrors(String text, Collection<String> words) {
        ArrayList<Error> errors = new ArrayList<>();
        Matcher matcher = WORD.matcher(text);
        int position = 0;
        while (matcher.find()) {
            String word = matcher.group();
            if (!words.contains(normalize(word))) {
                errors.add(new Error(word, position));
            }
            position++;
        }
        return errors;
    }
}
