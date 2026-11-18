/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Darius
 */
public class WordsStats {
    String word;
    int count;

    public WordsStats(String word) {
        this.word = word;
        this.count = 1;
    }

    @Override
    public String toString() {
        return word + " ---> " + count;
    }    
}
