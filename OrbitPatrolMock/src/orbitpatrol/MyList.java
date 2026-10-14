package orbitpatrol;

import java.awt.Graphics;

public class MyList {
    private Node root = null;
    private int size = 0;

    public Node getRoot() { return root; } // supplied

    public int sizeConstant() {
        // TODO C2_SIZE
        return 0;
    }
    public void clear() {
        // TODO C2_CLEAR
    }
    public void addToHead(Orb data) {
        // TODO C2_HEAD
    }
    public void addToTail(Orb data) {
        // TODO C2_TAIL
    }
    public void delete(int index) {
        // TODO C2_DELETE
    }

    public void move(int width, int height) {
        // TODO C3_MOVE
    }
    public void draw(Graphics g) {
        // TODO C3_DRAW
    }
    public Target findTargetHitBy(Bullet bullet) {
        // TODO C3_FIND_HIT
        return null;
    }

    /** Return the number removed, keeping surviving Orb/Node identities and order. */
    public int removeInactive() {
        // TODO C4_REMOVE_INACTIVE
        return 0;
    }

    /** Ascending x; selection sort; exchange Orb references, never individual attributes. */
    public void sortByX() {
        // TODO C6_SORT
    }
}
