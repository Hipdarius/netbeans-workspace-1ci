import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyList list = new MyList();
        list.add("Ada");
        list.add("Grace");
        list.insert("Alan", 1); // Value first, index second (as in the UML).
        list.set("Edsger", 1);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("Removed: " + list.remove(0));
        System.out.println("Found Grace: " + list.indexOf("Grace"));
        list.print();
    }
}
