import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/** Compare our implementation with Java's ArrayList after every operation. */
public class MyListTest {
    private static int checks;

    private static void check(boolean condition) {
        checks++;
        if (!condition) throw new AssertionError("Check " + checks);
    }

    public static void main(String[] args) {
        MyList actual = new MyList();
        ArrayList<Object> expected = new ArrayList<>();
        Random random = new Random(20261118);
        for (int step = 0; step < 5000; step++) {
            Object value = random.nextInt(5) == 0 ? null : Integer.valueOf(random.nextInt(12));
            int index = expected.isEmpty() ? 0 : random.nextInt(expected.size());
            switch (random.nextInt(8)) {
                case 0: check(actual.add(value) == expected.add(value)); break;
                case 1:
                    int insertion = random.nextInt(expected.size() + 1);
                    actual.insert(value, insertion); expected.add(insertion, value); break;
                case 2:
                    if (!expected.isEmpty()) {
                        check(java.util.Objects.equals(actual.remove(index), expected.remove(index)));
                    }
                    break;
                case 3: check(actual.remove(value) == expected.remove(value)); break;
                case 4:
                    if (!expected.isEmpty()) { actual.set(value, index); expected.set(index, value); }
                    break;
                case 5: check(actual.indexOf(value) == expected.indexOf(value)); break;
                case 6:
                    if (!expected.isEmpty()) check(java.util.Objects.equals(actual.get(index), expected.get(index)));
                    break;
                default:
                    if (random.nextInt(10) == 0) { actual.clear(); expected.clear(); }
            }
            check(actual.size() == expected.size());
            check(Arrays.equals(actual.toArray(), expected.toArray()));
        }
        actual.clear();
        expectBounds(() -> actual.get(0));
        expectBounds(() -> actual.remove(0));
        expectBounds(() -> actual.set("x", -1));
        expectBounds(() -> actual.insert("x", 1));
        actual.add("a");
        actual.add("b");
        Object[] copy = actual.toArray();
        copy[0] = "changed";
        check(actual.get(0).equals("a"));
        actual.remove(1); actual.add("c");
        check(Arrays.equals(actual.toArray(), new Object[]{"a", "c"}));
        System.out.println("MyListTest: " + checks + " checks passed");
    }

    private static void expectBounds(Runnable operation) {
        try { operation.run(); throw new AssertionError("Expected bounds error"); }
        catch (IndexOutOfBoundsException expected) { checks++; }
    }
}
