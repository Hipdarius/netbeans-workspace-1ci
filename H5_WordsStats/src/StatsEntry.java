/**
 * Un mot et son nombre d'occurrences.
 */
public class StatsEntry implements Comparable<StatsEntry> {

    public String word;
    public int count;

    public StatsEntry(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public int compareTo(StatsEntry other) {
        // Les mots les plus frequents apparaissent en premier.
        int result = Integer.compare(other.count, count);
        if (result == 0) {
            result = word.compareTo(other.word);
        }
        return result;
    }

    @Override
    public String toString() {
        return word + " --> " + count;
    }
}
