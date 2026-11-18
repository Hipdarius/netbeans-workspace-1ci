/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package h7_performancetests;

import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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
        
        //Initializing values
        Random random = new Random();
        
        for(int i = 0; i < size; i++) {
            // Reserve 997 and 998 so their first positions are known.
            values[i] = random.nextInt(max - min - 2) + min;
        }
        values[0] = max - 2;
        values[size / 2] = max - 1;
        values[size - 1] = max;

        //ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>();
        long alStart = Calendar.getInstance().getTimeInMillis();
        
        for (int i = 0; i < values.length; i++) {
            arrayList.add(values[i]);
        }
     
        long alEnd = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time in ms for Array List: " + (alEnd - alStart));
       
        
        //HashSet
        HashSet<Integer> hashSet = new HashSet<>();
        long hsStart = Calendar.getInstance().getTimeInMillis();
        
        for (int i = 0; i < values.length; i++) {
            hashSet.add(values[i]);
        }
        
        long hsEnd = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time in ms for Hash Set: " + (hsEnd - hsStart));
        
        
        //HashMap
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        long hmStart = Calendar.getInstance().getTimeInMillis();
        
        for (int i = 0; i < values.length; i++) {
            hashMap.put(i, values[i]);
        }
        
        long hmEnd = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time in ms for Hash Map: " + (hmEnd - hmStart)); 
        
        
        // Search the same data for a value at the beginning, in the middle,
        // and absent. 997 and 998 occur once; 1000 is outside [0, 999].
        int[] searches = {997, 998, 1000};
        String[] names = {"beginning", "middle", "absent"};
        for (int i = 0; i < searches.length; i++) {
            int target = searches[i];
            System.out.println("\nSearch " + names[i] + ": " + target);
            long start = System.nanoTime();
            boolean found = searchArray(values, target);
            printSearch("Array, our linear search", found, start);

            start = System.nanoTime();
            found = Arrays.stream(values).anyMatch(value -> value == target);
            printSearch("Array, stream anyMatch", found, start);

            start = System.nanoTime();
            found = searchArrayList(arrayList, target);
            printSearch("ArrayList, our linear search", found, start);

            start = System.nanoTime();
            found = arrayList.contains(target);
            printSearch("ArrayList.contains", found, start);

            start = System.nanoTime();
            found = searchHashSet(hashSet, target);
            printSearch("HashSet, our linear search", found, start);

            start = System.nanoTime();
            found = hashSet.contains(target);
            printSearch("HashSet.contains (hash lookup)", found, start);

            start = System.nanoTime();
            found = searchHashMap(hashMap, target);
            printSearch("HashMap, our linear search of values", found, start);

            start = System.nanoTime();
            found = hashMap.containsValue(target);
            printSearch("HashMap.containsValue", found, start);
        }
        System.out.println("\nHashSet stores " + hashSet.size() + " distinct values; "
                + "iteration order is not array order.");
        System.out.println("Times are illustrative: repeat runs and account for JVM warm-up.");
    }

    public static boolean searchArray(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) return true;
        }
        return false;
    }

    public static boolean searchArrayList(ArrayList<Integer> numbers, int target) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == target) return true;
        }
        return false;
    }

    public static boolean searchHashSet(HashSet<Integer> numbers, int target) {
        for (int number : numbers) {
            if (number == target) return true;
        }
        return false;
    }

    public static boolean searchHashMap(HashMap<Integer, Integer> numbers, int target) {
        for (int number : numbers.values()) {
            if (number == target) return true;
        }
        return false;
    }

    private static void printSearch(String name, boolean found, long start) {
        long elapsed = System.nanoTime() - start;
        System.out.println(name + ": " + found + " (" + elapsed + " ns)");
    }
}
