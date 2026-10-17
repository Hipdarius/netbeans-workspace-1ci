package orbitpatrol;

/** Same successor naming as your classroom Node: the field is named node. */
public class Node {
    private Orb data;
    private Node node;

    public Node(Orb data) {
        this.data = data;
    }
    public Orb getData() {
        return data;
    }
    public void setData(Orb data) {
        this.data = data;
    }
    public Node getNext() {
        return node;
    }
    public void setNext(Node node) {
        this.node = node;
    }
}
