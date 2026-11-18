import java.util.ArrayList;

public class WordsStatsList {
    private ArrayList<StatsEntry> entries = new ArrayList<>();

    public void calculate(String text) {
        entries.clear();
        text = text.toLowerCase().trim();

        if (text.isEmpty()) {
            return;
        }

        String[] words = text.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StatsEntry foundEntry = null;

            for (int j = 0; j < entries.size(); j++) {
                StatsEntry entry = entries.get(j);
                if (entry.word.equals(word)) {
                    foundEntry = entry;
                    break;
                }
            }

            if (foundEntry == null) {
                entries.add(new StatsEntry(word));
            } else {
                foundEntry.count++;
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        StatsEntry[] sortedEntries = entries.toArray(new StatsEntry[0]);

        for (int i = 0; i < sortedEntries.length - 1; i++) {
            for (int j = i + 1; j < sortedEntries.length; j++) {
                if (sortedEntries[j].count > sortedEntries[i].count) {
                    StatsEntry temp = sortedEntries[i];
                    sortedEntries[i] = sortedEntries[j];
                    sortedEntries[j] = temp;
                }
            }
        }

        for (int i = 0; i < sortedEntries.length; i++) {
            result += sortedEntries[i].toString() + "\n";
        }

        return result;
    }
}
