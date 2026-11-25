package h9_dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/** A dictionary with one index for each direction. */
public class DictionaryModel {
    public enum Structure { HASH_MAP, TREE_MAP, TREE_SET }
    public enum Direction { GERMAN_TO_FRENCH, FRENCH_TO_GERMAN }

    private final Index german;
    private final Index french;
    private int entryCount;

    public DictionaryModel(Structure structure) throws IOException {
        german = createIndex(structure);
        french = createIndex(structure);
        loadDictionary();
    }

    private void loadDictionary() throws IOException {
        InputStream stream = getClass().getResourceAsStream("DE-FR_dictionary_UTF8.txt");
        if (stream == null) {
            throw new IOException("Dictionary file was not found.");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while (line != null) {
                if (!line.isBlank()) {
                    addLine(line);
                }
                line = reader.readLine();
            }
        }
    }

    private void addLine(String line) throws IOException {
        String[] words = line.split(";", 2);
        if (words.length != 2 || words[0].isBlank() || words[1].isBlank()) {
            throw new IOException("Invalid dictionary line: " + line);
        }

        String germanWord = words[0].trim();
        String frenchWord = words[1].trim();
        german.add(germanWord.toLowerCase(Locale.ROOT), frenchWord);
        french.add(frenchWord.toLowerCase(Locale.ROOT), germanWord);
        entryCount++;
    }

    public List<String> translate(String word, Direction direction) {
        if (word == null || word.isBlank()) {
            return Collections.emptyList();
        }
        String key = word.trim().toLowerCase(Locale.ROOT);
        if (direction == Direction.GERMAN_TO_FRENCH) {
            return german.find(key);
        }
        return french.find(key);
    }

    public String translationText(String word, Direction direction) {
        if (word.isBlank()) {
            return "Enter a word or phrase to translate.";
        }
        List<String> translations = translate(word, direction);
        if (translations.isEmpty()) {
            return "No translation found.";
        }
        return String.join("\n", translations);
    }

    public int getEntryCount() {
        return entryCount;
    }

    private Index createIndex(Structure structure) {
        switch (structure) {
            case HASH_MAP:
                return new MapIndex(new HashMap<>());
            case TREE_MAP:
                return new MapIndex(new TreeMap<>());
            default:
                return new SetIndex();
        }
    }

    private interface Index {
        void add(String word, String translation);
        List<String> find(String word);
    }

    // A map needs a collection as its value to retain multiple translations.
    private static class MapIndex implements Index {
        private final Map<String, TreeSet<String>> entries;
        MapIndex(Map<String, TreeSet<String>> entries) {
            this.entries = entries;
        }

        public void add(String word, String translation) {
            TreeSet<String> translations = entries.get(word);
            if (translations == null) {
                translations = new TreeSet<>();
                entries.put(word, translations);
            }
            translations.add(translation);
        }
        public List<String> find(String word) {
            TreeSet<String> translations = entries.get(word);
            if (translations == null) {
                return Collections.emptyList();
            }
            return new ArrayList<>(translations);
        }
    }

    // Each pair must include the translation in its ordering, or TreeSet would
    // discard other translations for the same word as duplicates.
    private static class Entry implements Comparable<Entry> {
        final String word;
        final String translation;
        Entry(String word, String translation) {
            this.word = word;
            this.translation = translation;
        }
        public int compareTo(Entry other) {
            int comparison = word.compareTo(other.word);
            if (comparison == 0) {
                return translation.compareTo(other.translation);
            }
            return comparison;
        }
    }

    private static class SetIndex implements Index {
        private final TreeSet<Entry> entries = new TreeSet<>();
        public void add(String word, String translation) {
            entries.add(new Entry(word, translation));
        }

        public List<String> find(String word) {
            List<String> result = new ArrayList<>();
            // Start at the first possible translation for this word.
            Entry first = new Entry(word, "");
            for (Entry entry : entries.tailSet(first)) {
                if (!entry.word.equals(word)) {
                    break;
                }
                result.add(entry.translation);
            }
            return result;
        }
    }
}
