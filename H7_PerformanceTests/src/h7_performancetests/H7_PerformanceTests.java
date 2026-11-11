/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package h7_performancetests;

import static java.nio.file.Files.size;
import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Darius
 */
public class H7_PerformanceTests {
    
    static int size = 1000000;
    static int min = 0;
    static int max = 999;
        
    static int[] values = new int[size];  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Random random = new Random();
        
        for(int i = 0; i < size; i++) {
            values[i] = random.nextInt(max - min + 1) + min;
        }
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        long alStart = Calendar.getInstance().getTimeInMillis();
        
        for (int i = 0; i < values.length; i++) {
            arrayList.add(values[i]);
        }
     
        long alEnd = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time in ms for Array List: " + (alEnd - alStart));
       
        
        HashSet<Integer> hashSet = new HashSet<>();
        long hsStart = Calendar.getInstance().getTimeInMillis();
        long hsEnd = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time in ms for Hash Set: " + (hsEnd - hsStart));
        
        
    }
    
}
