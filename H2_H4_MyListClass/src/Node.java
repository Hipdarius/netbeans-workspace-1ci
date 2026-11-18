
public class Node {
    private int data;
    private Node node;

    public Node(int data) {
        this.data = data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNext(Node node) {
        this.node = node;
    }

    public int getData() {
        return data;
    }

    public Node getNext() {
        return node;
    }
}
