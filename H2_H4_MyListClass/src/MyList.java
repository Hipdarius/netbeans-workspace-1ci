import com.google.gson.Gson;
import java.util.Objects;

/** A singly linked list. H4 keeps the last node and size up to date. */
public class MyList {
    private Node root = null;
    private Node last = null;
    private int size = 0;

    // O(1): last points to the last node, even after a deletion or reversal.
    public boolean add(Object data) {
        Node n = new Node(data);
        if (root == null) {
            root = n;
        } else {
            last.setNext(n);
        }
        last = n;
        size++;
        return true;
    }

    public int size() { return size; }
    public int sizeConstant() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void print() {
        Node tmp = root;
        while (tmp != null) {
            System.out.println(tmp.getData());
            tmp = tmp.getNext();
        }
    }

    public void clear() {
        root = null;
        last = null;
        size = 0;
    }

    // O(n), private as in the exercise UML.
    private Node getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(index);
        Node tmp = root;
        for (int i = 0; i < index; i++) tmp = tmp.getNext();
        return tmp;
    }

    public Object get(int index) { return getNode(index).getData(); }

    // The sheet uses set(value, index); the class GUI uses replace(index, value).
    public void set(Object data, int index) { getNode(index).setData(data); }
    public void replace(int index, Object data) { set(data, index); }

    public Object remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(index);
        Node removed;
        if (index == 0) {
            removed = root;
            root = root.getNext();
            if (size == 1) last = null;
        } else {
            Node previous = getNode(index - 1);
            removed = previous.getNext();
            previous.setNext(removed.getNext());
            if (removed == last) last = previous;
        }
        size--;
        return removed.getData();
    }

    public void delete(int index) { remove(index); }

    // Remove the first equal value. Objects.equals also handles null.
    public boolean remove(Object data) {
        int index = indexOf(data);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    public int indexOf(Object data) {
        Node tmp = root;
        int index = 0;
        while (tmp != null) {
            if (Objects.equals(tmp.getData(), data)) return index;
            tmp = tmp.getNext();
            index++;
        }
        return -1;
    }

    public boolean contains(Object data) { return indexOf(data) != -1; }

    public int count(Object data) {
        int count = 0;
        Node tmp = root;
        while (tmp != null) {
            if (Objects.equals(tmp.getData(), data)) count++;
            tmp = tmp.getNext();
        }
        return count;
    }

    // The PDF order is insert(value, index).
    public void insert(Object data, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException(index);
        if (index == size) {
            add(data);
        } else if (index == 0) {
            addToHead(data);
        } else {
            Node previous = getNode(index - 1);
            Node n = new Node(data);
            n.setNext(previous.getNext());
            previous.setNext(n);
            size++;
        }
    }

    // Named separately to avoid confusing the PDF's value-first insert signature.
    public void insertAtIndex(int index, Object data) { insert(data, index); }

    public void addToHead(Object data) {
        Node n = new Node(data);
        n.setNext(root);
        root = n;
        if (size == 0) last = n;
        size++;
    }

    public void addToTail(Object data) { add(data); }
    public Node getRoot() { return root; }

    public Object[] toArray() {
        Object[] result = new Object[size];
        Node tmp = root;
        for (int i = 0; i < size; i++) {
            result[i] = tmp.getData();
            tmp = tmp.getNext();
        }
        return result;
    }

    // The existing Swing tester only accepts integers; the model accepts any Object.
    public Integer[] toIntegerArray() {
        Integer[] result = new Integer[size];
        Node tmp = root;
        for (int i = 0; i < size; i++) {
            result[i] = (Integer) tmp.getData();
            tmp = tmp.getNext();
        }
        return result;
    }

    // Selection sort, as in the original class code: O(n^2).
    // Values must be mutually Comparable (all Integer, or all String, etc.).
    public void sort() {
        for (Node i = root; i != null && i.getNext() != null; i = i.getNext()) {
            Node nodeMin = i;
            for (Node j = i.getNext(); j != null; j = j.getNext()) {
                if (compare(j.getData(), nodeMin.getData()) < 0) nodeMin = j;
            }
            Object tmp = i.getData();
            i.setData(nodeMin.getData());
            nodeMin.setData(tmp);
        }
    }

    @SuppressWarnings("unchecked")
    private int compare(Object a, Object b) {
        if (a == b) return 0;
        if (a == null) return -1;
        if (b == null) return 1;
        return ((Comparable<Object>) a).compareTo(b);
    }

    public boolean verifySort() {
        Node tmp = root;
        while (tmp != null && tmp.getNext() != null) {
            if (compare(tmp.getData(), tmp.getNext().getData()) > 0) return false;
            tmp = tmp.getNext();
        }
        return true;
    }

    public void reverse() {
        Node previous = null;
        Node current = root;
        last = root;
        while (current != null) {
            Node next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        root = previous;
    }

    public String toJson() { return new Gson().toJson(toArray()); }

    // XML stores the text of each value. Null has a separate marker.
    public String toXML() {
        StringBuilder xml = new StringBuilder("<list>");
        Node tmp = root;
        while (tmp != null) {
            Object data = tmp.getData();
            if (data == null) {
                xml.append("<item null=\"true\"/>");
            } else {
                String text = data.toString();
                for (int i = 0; i < text.length();) {
                    int c = text.codePointAt(i);
                    if (!(c == 9 || c == 10 || c == 13 || (c >= 32 && c <= 0xD7FF)
                            || (c >= 0xE000 && c <= 0xFFFD) || (c >= 0x10000 && c <= 0x10FFFF))) {
                        throw new IllegalArgumentException("Character cannot be represented in XML 1.0");
                    }
                    i += Character.charCount(c);
                }
                text = text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                        .replace("\"", "&quot;").replace("'", "&apos;");
                xml.append("<item>").append(text).append("</item>");
            }
            tmp = tmp.getNext();
        }
        return xml.append("</list>").toString();
    }
}
