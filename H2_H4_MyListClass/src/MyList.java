public class MyList {

    private Node root = null;
    private Node last = null;
    private int size;

    // H4: last lets us append without walking through the list.
    public void add(int number) {
        Node node = new Node(number);
        if (root == null) {
            root = node;
        } else {
            last.setNext(node);
        }
        last = node;
        size++;
    }

    // Time: O(n), Space: O(1)
    public void print() {
        Node tmp = root;

        while (tmp != null) {
            System.out.println(tmp.getData());
            tmp = tmp.getNext();
        }
    }

    public void delete(int index) {
        remove(index);
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }

        Node removed;
        if (index == 0) {
            removed = root;
            root = root.getNext();
            if (root == null) {
                last = null;
            }
        } else {
            Node previous = getNode(index - 1);
            removed = previous.getNext();
            previous.setNext(removed.getNext());
            if (removed == last) {
                last = previous;
            }
        }
        size--;
        return removed.getData();
    }

    // Both the value and the index are ints, so use a separate method name.
    public boolean removeValue(int data) {
        int index = indexOf(data);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    // H4: the count is maintained when nodes are added or removed.
    public int size() {
        return size;
    }

    public int sizeConstant() {
        return size();
    }

    public void clear() {
        root = null;
        last = null;
        size = 0;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        Node node = root;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    public int get(int index) {
        return getNode(index).getData();
    }

    public void replace(int index, int data) {
        getNode(index).setData(data);
    }

    public void set(int data, int index) {
        replace(index, data);
    }

    // Time: O(1), Space: O(1)
    public void addToHead(int data) {
        Node n = new Node(data);

        n.setNext(root);
        root = n;
        if (last == null) {
            last = n;
        }

        size++;
    }

    // Time: O(1), Space: O(1)
    public void addToTail(int data) {
        add(data);
    }

    // Time: O(1), Space: O(1)
    public Node getRoot() {
        return root;
    }


    // Time: O(n^2), Space: O(1)
    public void sort() {
        for(Node i = root; i != null && i.getNext() != null; i=i.getNext()) {
            Node nodeMin = i;

            for(Node j = i.getNext(); j != null; j = j.getNext()) {
                if(j.getData() < nodeMin.getData()) {
                    nodeMin = j;
                }
            }

            if(nodeMin != i) {
                int tmp = i.getData();
                i.setData(nodeMin.getData());
                nodeMin.setData(tmp);
            }
        }
    }

    // Time: O(n), Space: O(n)
    public Integer[] toArray() {
        Integer[] result = new Integer[size];

        Node tmp = root;
        for(int i = 0; i < size; i++) {
            result[i] = tmp.getData();
            tmp = tmp.getNext();
        }
        return result;
    }

    // Time: O(n), Space: O(1)
    public boolean contains(int data) {
        return indexOf(data) != -1;
    }

    // Time: O(n), Space: O(1)
    public int indexOf(int data) {
        Node tmp = root;
        int index = 0;

        while (tmp != null) {
            if (tmp.getData() == data) {
                return index;
            }

            tmp = tmp.getNext();
            index++;
        }

        return -1;
    }

    // Keep the argument order used in the integer project.
    public void insert(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index);
        }

        if (index == size) {
            add(data);
        } else if (index == 0) {
            addToHead(data);
        } else {
            Node previous = getNode(index - 1);
            Node node = new Node(data);
            node.setNext(previous.getNext());
            previous.setNext(node);
            size++;
        }
    }

    public int count(int data) {
        int count = 0;
        Node node = root;
        while (node != null) {
            if (node.getData() == data) {
                count++;
            }
            node = node.getNext();
        }
        return count;
    }

    public boolean verifySort() {
        Node node = root;
        while (node != null && node.getNext() != null) {
            if (node.getData() > node.getNext().getData()) {
                return false;
            }
            node = node.getNext();
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

    public boolean isEmpty() {
        return root == null;
    }

    public String toJson() {
        // For integers the array's text is also valid JSON (e.g. [10, -2]).
        return java.util.Arrays.toString(toArray());
    }

    public String toXML() {
        String xml = "<list>";
        Node node = root;
        while (node != null) {
            xml += "<item>" + node.getData() + "</item>";
            node = node.getNext();
        }
        return xml + "</list>";
    }
}
