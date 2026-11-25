package h8_spellchecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class TreeSetSpellChecker extends SpellChecker {
    private final TreeSet<String> tsWords = new TreeSet<>();

    public TreeSetSpellChecker() throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader("DE_Wortliste_UTF8.txt"))) {
            String line;
            while((line = in.readLine()) != null) {
                tsWords.add(line.trim());
            }
        }
    }

    @Override
    public ArrayList<Error> analyseText(String text) {
        long start = Calendar.getInstance().getTimeInMillis();
        
        ArrayList<Error> errors = new ArrayList<>();
        String[] words = text.split("\\W+");
        
        for(int i = 0; i < words.length; i++) {
            
        }
    }
}
