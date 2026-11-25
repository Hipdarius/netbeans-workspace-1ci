package h8_spellchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Shared text analysis; the collection passed to analyseText determines lookup speed. */
public class SpellChecker {
    private static final Pattern WORD = Pattern.compile("\\p{L}[\\p{L}\\p{M}]*");
    private final ArrayList<String> arrayWords = new ArrayList<>();
    private final TreeSet<String> treeWords = new TreeSet<>();

    public SpellChecker(Path file) throws IOException {
        loadWords(file);
    }

    private void loadWords(Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = normalize(line.replace("\uFEFF", "").trim());
                if (!word.isEmpty()) {
                    arrayWords.add(word);
                    treeWords.add(word);
                }
            }
        }
    }

    private String normalize(String word) {
        return Normalizer.normalize(word, Normalizer.Form.NFC).toLowerCase(Locale.GERMAN);
    }

    /** Finds each occurrence; positions are zero-based and end is exclusive. */
    public List<Misspelling> analyseText(String text, boolean useTreeSet) {
        List<Misspelling> result = new ArrayList<>();
        Collection<String> dictionary = arrayWords;
        if (useTreeSet) {
            dictionary = treeWords;
        }
        Matcher matcher = WORD.matcher(text);
        while (matcher.find()) {
            String word = matcher.group();
            String key = normalize(word);
            if (!dictionary.contains(key)) {
                result.add(new Misspelling(word, matcher.start(), matcher.end()));
            }
        }
        return result;
    }

    public int getArraySize() {
        return arrayWords.size();
    }

    public int getTreeSize() {
        return treeWords.size();
    }

    public static class Misspelling {
        public final String word;
        public final int start;
        public final int end;
        public Misspelling(String word, int start, int end) {
            this.word = word;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return word + " (positions " + (start + 1) + "–" + end + ")";
        }
    }
}
