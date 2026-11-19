package boardclub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeSet;

public class NotesTools {
    private final ArrayList<String> wordList = new ArrayList<>();
    private final TreeSet<String> wordSet = new TreeSet<>();
    private final HashMap<String, ArrayList<String>> dictionary = new HashMap<>();

    // Supplied file reading. addTranslation is completed in D3.
    public void loadWords(String fileName) throws IOException {
        wordList.clear();
        wordSet.clear();
        for (String line : Files.readAllLines(Path.of(fileName))) {
            String word = line.trim().toLowerCase(Locale.ROOT);
            if (!word.isEmpty()) {
                wordList.add(word);
                wordSet.add(word);
            }
        }
    }

    public void loadDictionary(String fileName) throws IOException {
        dictionary.clear();
        for (String line : Files.readAllLines(Path.of(fileName))) {
            if (!line.isBlank()) {
                String[] parts = line.split(";", 2);
                addTranslation(parts[0], parts[1]);
            }
        }
    }

    public ArrayList<String> translations(String german) {
        ArrayList<String> result = dictionary.get(german.trim().toLowerCase(Locale.ROOT));
        if (result == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(result);
    }

    // Provided. Read a run of letters, then remember where it began.
    public ArrayList<WordHit> words(String text) {
        ArrayList<WordHit> result = new ArrayList<>();
        int index = 0;
        while (index < text.length()) {
            if (!Character.isLetter(text.charAt(index))) {
                index++;
            } else {
                int start = index;
                while (index < text.length() && Character.isLetter(text.charAt(index))) {
                    index++;
                }
                String word = text.substring(start, index).toLowerCase(Locale.ROOT);
                result.add(new WordHit(word, start));
            }
        }
        return result;
    }

    // Provided. Selection sort for display.
    private void sortCounts(ArrayList<StatsEntry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            int best = i;
            for (int j = i + 1; j < entries.size(); j++) {
                StatsEntry a = entries.get(j);
                StatsEntry b = entries.get(best);
                if (a.count > b.count || (a.count == b.count && a.word.compareTo(b.word) < 0)) {
                    best = j;
                }
            }
            StatsEntry saved = entries.get(i);
            entries.set(i, entries.get(best));
            entries.set(best, saved);
        }
    }

    public ArrayList<StatsEntry> countWords(String text, boolean useMap) {
        // TODO D1
        return new ArrayList<>();
    }

    public ArrayList<WordHit> findUnknown(String text, boolean useTree) {
        // TODO D2
        return new ArrayList<>();
    }

    public void addTranslation(String german, String french) {
        // TODO D3
    }
}
