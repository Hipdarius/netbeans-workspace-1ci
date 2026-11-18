
public class Node {
    private Object data;
    private Node node;

    public Node(Object data) {
        this.data = data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setNext(Node node) {
        this.node = node;
    }

    public Object getData() {
        return data;
    }

    public Node getNext() {
        return node;
    }
}
