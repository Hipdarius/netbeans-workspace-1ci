package h5_wordstats;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Les deux solutions de l'exercice H.5 utilisent les memes regles de lecture.
 */
public class WordsStats {

    private static final Pattern WORD_PATTERN =
            Pattern.compile("\\p{L}[\\p{L}\\p{M}]*");

    /**
     * Version 1 : le mot est la cle, son nombre d'occurrences est la valeur.
     */
    public ArrayList<StatsEntry> calculateWithHashMap(String text) {
        HashMap<String, Integer> counts = new HashMap<>();

        for (String word : getWords(text)) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }

        ArrayList<StatsEntry> statistics = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            statistics.add(new StatsEntry(entry.getKey(), entry.getValue()));
        }

        Collections.sort(statistics);
        return statistics;
    }

    /**
     * Version 2 : recherche lineaire dans une liste, sans table associative.
     */
    public ArrayList<StatsEntry> calculateWithList(String text) {
        ArrayList<StatsEntry> statistics = new ArrayList<>();

        for (String word : getWords(text)) {
            StatsEntry foundEntry = null;
            for (StatsEntry entry : statistics) {
                if (entry.word.equals(word)) {
                    foundEntry = entry;
                    break;
                }
            }

            if (foundEntry == null) {
                statistics.add(new StatsEntry(word, 1));
            } else {
                foundEntry.count++;
            }
        }

        Collections.sort(statistics);
        return statistics;
    }

    private static ArrayList<String> getWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return words;
        }

        // NFC rend identiques les deux encodages possibles d'un accent.
        String normalizedText = Normalizer.normalize(
                text.toLowerCase(Locale.ROOT), Normalizer.Form.NFC);
        Matcher matcher = WORD_PATTERN.matcher(normalizedText);
        while (matcher.find()) {
            words.add(matcher.group());
        }
        return words;
    }
}
