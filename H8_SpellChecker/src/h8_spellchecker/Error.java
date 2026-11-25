package h8_spellchecker;

public class Error {
    public final String word;
    public final int position;

    public Error(String word, int position) {
        this.word = word;
        this.position = position;
    }

    @Override
    public String toString() {
        return word + " (word index " + position + ")";
    }
}
