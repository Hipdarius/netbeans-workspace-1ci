package lu.scipr.dome.model;

import java.io.IOException;
import java.util.HashMap;

public class Schedule {
    private Node root;
    private int size;

    public int size() {
        return size;
    }

    // For building a draft list from fresh shows.
    public void append(Show show) {
        Node added = new Node(show);
        if (root == null) {
            root = added;
        } else {
            Node tail = root;
            while (tail.getNext() != null) {
                tail = tail.getNext();
            }
            tail.setNext(added);
        }
        size++;
    }

    public void clearPlan() {
        for (Node node = root; node != null; node = node.getNext()) {
            node.getShow().setSlot(0, -1);
        }
    }

    public void addInOrder(Show show) {
        // TODO B4
    }

    public Node getNode(int index) {
        // TODO B5
        return null;
    }

    public void swap(Node a, Node b) {
        // TODO B6
    }

    public void sortByRequest() {
        // TODO B7
    }

    public void plan() {
        // TODO B8
    }

    public Show[] toArray() {
        // TODO B9
        return new Show[0];
    }

    public HashMap<String, Integer> countByGroup() {
        // TODO B10
        return new HashMap<String, Integer>();
    }

    public void saveToFile(String filename) throws IOException {
        // TODO B11
        throw new IOException("Save is unfinished (B11).");
    }

    public void loadFromFile(String filename) throws IOException {
        // TODO B12
        throw new IOException("Load is unfinished (B12).");
    }

    // Validates a single record after it has been split into fields.
    public static Show fromFields(String[] fields) throws IOException {
        if (fields == null || fields.length != 6) {
            throw new IOException("Six fields are required.");
        }
        String kind = fields[0];
        String title = fields[1];
        String group = fields[2];
        if (!validText(title) || !validText(group)) {
            throw new IOException("Check the title and group.");
        }
        int requested = decimal(fields[3]);
        int minutes = decimal(fields[4]);
        int setup = decimal(fields[5]);
        if (minutes < 1) {
            throw new IOException("The running time must be positive.");
        }
        if ("R".equals(kind) && setup == 0) {
            return new Show(title, group, requested, minutes);
        }
        if ("L".equals(kind) && setup > 0) {
            return new LiveShow(title, group, requested, minutes, setup);
        }
        throw new IOException("Check the show type and setup minutes.");
    }

    private static boolean validText(String text) {
        return text != null && text.matches("[A-Za-z0-9]+( +[A-Za-z0-9]+)*");
    }

    private static int decimal(String text) throws IOException {
        if (text == null || !text.matches("0|[1-9][0-9]*")) {
            throw new IOException("Enter a whole number without leading zeros.");
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            throw new IOException("The number is too large.", ex);
        }
    }
}
