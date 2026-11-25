package h8_spellchecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class HashSetSpellChecker extends SpellChecker {
    private final HashSet<String> hsWords = new HashSet<>();

    public HashSetSpellChecker() throws IOException {
        loadWords(hsWords);
    }

    @Override
    public ArrayList<Error> analyseText(String text) {
        return findErrors(text, hsWords);
    }
}
