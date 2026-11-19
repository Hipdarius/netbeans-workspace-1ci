package boardclub;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayerList {
    private Node root;
    private Node last;
    private int size;

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        last = null;
        size = 0;
    }

    public void add(Player player) {
        // TODO B1
    }

    private Node getNode(int index) {
        // TODO B2
        throw new IndexOutOfBoundsException(index);
    }

    public Player get(int index) {
        // TODO B2
        return null;
    }

    public void addInOrder(Player player) {
        // TODO B3
    }

    public Player remove(int index) {
        // TODO B4
        throw new IndexOutOfBoundsException(index);
    }

    public void sortByName() {
        // TODO B5
    }

    public void reverse() {
        // TODO B6
    }

    public Player[] toArray() {
        // TODO B7
        return new Player[0];
    }

    public void saveToFile(String fileName) throws IOException {
        // TODO C1
    }

    public void loadFromFile(String fileName) throws IOException {
        // TODO C2
    }
}
