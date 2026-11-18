public class MyListTest {
    public static void main(String[] args) {
        MyList list = new MyList();
        checkInvalidReplace(list, 0);
        list.add(30);
        list.add(10);
        list.replace(1, 20);
        checkInvalidReplace(list, -1);
        checkInvalidReplace(list, 2);
        if(list.get(0) != 30 || list.get(1) != 20 || list.sizeConstant() != 2) {
            throw new AssertionError("Replacement changed the wrong item or size");
        }
        list.insert(1, 10);
        list.sort();
        list.delete(1);
        if(list.get(0) != 10 || list.get(1) != 30 || list.size() != list.sizeConstant()) {
            throw new AssertionError("Insert, sort or delete failed");
        }
        System.out.println("MyList regression checks passed");
    }

    private static void checkInvalidReplace(MyList list, int index) {
        try {
            list.replace(index, 99);
            throw new AssertionError("Accepted invalid index: " + index);
        } catch(IndexOutOfBoundsException expected) {
            // Invalid indexes must be rejected without changing the list.
        }
    }
}
