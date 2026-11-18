import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyList list = new MyList();
        list.add(30);
        list.add(10);
        list.add(10);
        list.insert(20, 1);
        System.out.println("Original: " + Arrays.toString(list.toArray()));
        System.out.println("Count of 10: " + list.count(10));
        list.sort();
        System.out.println("Sorted: " + list.verifySort() + " " + list.toJson());
        list.reverse();
        System.out.println("Reversed: " + list.toXML());
        // remove(10) means index 10! Box the number to remove by value.
        list.remove(Integer.valueOf(10));
        System.out.println("After removing first 10: " + list.toJson());
    }
}
