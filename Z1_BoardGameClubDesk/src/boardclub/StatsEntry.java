package boardclub;

// Provided in full.
public class StatsEntry {
    public final String word;
    public int count;

    public StatsEntry(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public String toString() {
        return word + ": " + count;
    }
}
