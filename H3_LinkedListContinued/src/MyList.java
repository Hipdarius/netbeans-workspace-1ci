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

    // H3: compare values by equals, including null.
    public int count(Object value) {
        int result = 0;
        for (Node current = root; current != null; current = current.getNext()) {
            if (Objects.equals(current.getData(), value)) result++;
        }
        return result;
    }

    public boolean isEmpty() { return root == null; }

    // Natural order requires mutually comparable values (e.g. all Integer).
    // Null sorts before other values. Object itself has no natural order.
    @SuppressWarnings("unchecked")
    private int compare(Object left, Object right) {
        if (left == right) return 0;
        if (left == null) return -1;
        if (right == null) return 1;
        return ((Comparable<Object>) left).compareTo(right);
    }

    // Selection sort: O(n^2) comparisons, O(1) extra space.
    public void sort() {
        for (Node first = root; first != null; first = first.getNext()) {
            Node minimum = first;
            for (Node current = first.getNext(); current != null; current = current.getNext()) {
                if (compare(current.getData(), minimum.getData()) < 0) minimum = current;
            }
            Object temporary = first.getData();
            first.setData(minimum.getData());
            minimum.setData(temporary);
        }
    }

    public boolean verifySort() {
        for (Node current = root; current != null && current.getNext() != null;
                current = current.getNext()) {
            if (compare(current.getData(), current.getNext().getData()) > 0) return false;
        }
        return true;
    }

    // Save next BEFORE redirecting the link; otherwise the rest of the list is lost.
    public void reverse() {
        Node previous = null;
        Node current = root;
        while (current != null) {
            Node next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        root = previous;
    }

    public String toJson() {
        // Serialize values, not the internal Node chain.
        return new com.google.gson.Gson().toJson(toArray());
    }

    public String toXML() {
        StringBuilder result = new StringBuilder("<list>");
        for (Node current = root; current != null; current = current.getNext()) {
            Object value = current.getData();
            if (value == null) result.append("<item null=\"true\"/>");
            else result.append("<item>").append(escapeXML(value.toString())).append("</item>");
        }
        return result.append("</list>").toString();
    }

    private String escapeXML(String text) {
        // XML 1.0 cannot represent every Java character (e.g. NUL).
        for (int i = 0; i < text.length();) {
            int code = text.codePointAt(i);
            if (!(code == 9 || code == 10 || code == 13 ||
                    (code >= 32 && code <= 0xD7FF) ||
                    (code >= 0xE000 && code <= 0xFFFD) ||
                    (code >= 0x10000 && code <= 0x10FFFF))) {
                throw new IllegalArgumentException("Character cannot be represented in XML 1.0");
            }
            i += Character.charCount(code);
        }
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&apos;");
    }
}
