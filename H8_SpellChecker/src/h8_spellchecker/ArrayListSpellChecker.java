package h8_spellchecker;

import java.io.IOException;
import java.util.ArrayList;

public class ArrayListSpellChecker extends SpellChecker {
    private final ArrayList<String> alWords = new ArrayList<>();

    public ArrayListSpellChecker() throws IOException {
        loadWords(alWords);
    }

    @Override
    public ArrayList<Error> analyseText(String text) {
        return findErrors(text, alWords);
    }
}
