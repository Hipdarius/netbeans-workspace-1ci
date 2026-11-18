
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Darius
 */
public class WordsStats {
    private HashMap<String, StatsEntry> entries = new HashMap<>();
    
    public void calculate(String text) {
        entries.clear();
        
        text = text.toLowerCase().trim();
        
        if(text.isEmpty()) {
            return;
        }
        
        String[] words = text.split("\\s+");
        
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            
            StatsEntry entry = entries.get(word);
            
            if(entry == null) {
                entries.put(word, new StatsEntry(word));
            } else {
                entry.count++;
            }
        }
    }
    
    @Override
    public String toString() {
        String result = "";
        
        String[] words = entries.keySet().toArray(new String[0]);
        
        for(int i = 0; i < words.length; i++) {
            StatsEntry entry = entries.get(words[i]);
            result += entry.toString() + "\n";
        }
        
        return result;
    }
}
