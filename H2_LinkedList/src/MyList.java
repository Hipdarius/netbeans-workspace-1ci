import java.util.Objects;

/** H2: singly linked list matching the sheet's UML (Object values). */
public class MyList {
    private Node root;

    // O(n): without a tail reference we must find the last node.
    public boolean add(Object value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
        } else {
            Node current = root;
            while (current.getNext() != null) current = current.getNext();
            current.setNext(node);
        }
        return true;
    }

    public int size() {
        int count = 0;
        for (Node current = root; current != null; current = current.getNext()) count++;
        return count;
    }

    private Node getNode(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index: " + index);
        Node current = root;
        for (int i = 0; i < index && current != null; i++) current = current.getNext();
        if (current == null) throw new IndexOutOfBoundsException("Index: " + index);
        return current;
    }

    public Object get(int index) { return getNode(index).getData(); }
    public void set(Object value, int index) { getNode(index).setData(value); }

    public void insert(Object value, int index) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException("Index: " + index);
        Node node = new Node(value);
        if (index == 0) {
            node.setNext(root);
            root = node;
        } else {
            Node previous = getNode(index - 1);
            node.setNext(previous.getNext());
            previous.setNext(node);
        }
    }

    public Object remove(int index) {
        // Validate before changing any links.
        Node removed = getNode(index);
        if (index == 0) root = root.getNext();
        else getNode(index - 1).setNext(removed.getNext());
        return removed.getData();
    }

    public boolean remove(Object value) {
        int index = indexOf(value);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    public int indexOf(Object value) {
        int index = 0;
        for (Node current = root; current != null; current = current.getNext()) {
            if (Objects.equals(current.getData(), value)) return index;
            index++;
        }
        return -1;
    }

    public void print() {
        for (Node current = root; current != null; current = current.getNext()) {
            System.out.println(current.getData());
        }
    }

    public void clear() { root = null; }

    public Object[] toArray() {
        Object[] result = new Object[size()];
        Node current = root;
        for (int i = 0; i < result.length; i++) {
            result[i] = current.getData();
            current = current.getNext();
        }
        return result; // Copy of the structure; element objects are shared.
    }
}
