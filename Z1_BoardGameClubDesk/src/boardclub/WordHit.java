package boardclub;

// Provided in full. word is normalized; position is an offset in the source text.
public class WordHit {
    public final String word;
    public final int position;

    public WordHit(String word, int position) {
        this.word = word;
        this.position = position;
    }

    @Override
    public String toString() {
        return word + " @" + position;
    }
}
