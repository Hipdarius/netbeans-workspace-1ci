package h7_performancetests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SearchTest {
    public static void main(String[] args) {
        int[] values = {17, 3, 42, 8, 9};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(17, 3, 42, 8, 9));
        HashSet<Integer> set = new HashSet<>(list);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) map.put(i, values[i]);
        for (int target : new int[]{17, 42, 9, 1000}) {
            boolean expected = list.contains(target);
            if (H7_PerformanceTests.searchArray(values, target) != expected
                    || H7_PerformanceTests.searchArrayList(list, target) != expected
                    || H7_PerformanceTests.searchHashSet(set, target) != expected
                    || H7_PerformanceTests.searchHashMap(map, target) != expected) {
                throw new AssertionError("Wrong result for " + target);
            }
        }
        // 0 is a map key but not a value; the search must examine values.
        if (H7_PerformanceTests.searchHashMap(map, 0)) throw new AssertionError("Searched keys");
        System.out.println("SearchTest passed");
    }
}
