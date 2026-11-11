/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package h7_performancetests;

/**
 *
 * @author Darius
 */
public class H7_PerformanceTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long start = System.nanoTime();
        
        public static final int[] SOURCE_DATA = ThreadLocalRandom.current()
                .ints(1_000_000, 0, 1000)
                .toArray();
        
        
        
        long end = System.nanoTime();
        System.out.println("Time in ms: " + (end - start));
    }
    
}
