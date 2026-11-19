package boardclub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class SearchLab {
    public static boolean linearSearch(int[] numbers, int target) {
        // TODO F3a
        return false;
    }

    public static void run() {
        int[] numbers = new int[1000000];
        Random random = new Random(7);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(998);
        }
        numbers[0] = 998;
        numbers[numbers.length / 2] = 999;

        ArrayList<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
            set.add(numbers[i]);
            map.put(i, numbers[i]);
        }

        for (int target : new int[]{998, 999, 1000}) {
            compare(numbers, list, set, map, target);
        }
    }

    private static void compare(int[] numbers, ArrayList<Integer> list,
            HashSet<Integer> set, HashMap<Integer, Integer> map, int target) {
        // TODO F3b
        System.out.println("Search target " + target);
    }

    public static void main(String[] args) {
        run();
    }
}
