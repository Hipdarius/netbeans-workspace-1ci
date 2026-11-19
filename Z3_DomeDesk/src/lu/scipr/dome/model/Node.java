package lu.scipr.dome.model;

public class Node {
    private Show show;
    private Node next;

    public Node(Show show) {
        this.show = show;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
