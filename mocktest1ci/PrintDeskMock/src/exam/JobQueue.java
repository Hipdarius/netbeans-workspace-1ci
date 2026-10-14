package exam;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class JobQueue {
    private Node root;
    private int size;
    public Node getRoot() { return root; }
    public PrintJob get(int index) { return getNode(index).getData(); }
    public PrintJob[] toArray() {
        PrintJob[] result = new PrintJob[size];
        Node tmp = root;
        for (int i=0;i<size;i++) { result[i]=tmp.getData(); tmp=tmp.getNext(); }
        return result;
    }
    public int sizeConstant() {
        return size;
    }
    public void clear() {
        root = null;
        size = 0;
    }
    private Node getNode(int index) {
        // TODO C2
        throw new UnsupportedOperationException("TODO C2");
    }
    public void add(PrintJob data) {
        // TODO C2
    }
    public PrintJob pop() {
        // TODO C2
        return null;
    }
    public void delete(int index) {
        // TODO C2
    }
    public int indexOf(String name) {
        // TODO C3
        return 0;
    }
    public double averageDuration() {
        // TODO C3
        return 0.0;
    }
    public void sortByDuration() {
        // TODO C3
    }
    public void prioritise() {
        // TODO C4
    }
    public void saveToFile(String fileName) throws IOException {
        // TODO C7
    }
}
