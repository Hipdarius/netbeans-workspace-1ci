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
        list.insert(2, -5);
        list.add(-5);
        if (list.verifySort() || list.count(-5) != 2 || !list.removeValue(-5)) {
            throw new AssertionError("Count, order or remove by value failed");
        }
        list.reverse();
        list.add(7);
        if (list.remove(3) != 7 || list.remove(2) != 10) {
            throw new AssertionError("Reverse or tail removal failed");
        }
        list.add(20);
        list.set(-6, 0);
        if (list.size() != 3 || !list.toJson().equals("[-6, 30, 20]")
                || !list.toXML().equals("<list><item>-6</item><item>30</item><item>20</item></list>")) {
            throw new AssertionError("Append, replace or export failed");
        }
        list.clear();
        list.reverse();
        list.addToHead(11);
        list.remove(0);
        list.add(12);
        if (list.isEmpty() || list.size() != 1 || list.get(0) != 12) {
            throw new AssertionError("Cannot reuse an empty list");
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
