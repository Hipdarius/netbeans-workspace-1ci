package orbitpatrol;

import java.awt.Graphics;

public class MyList {
    private Node root = null;
    private int size = 0;

    public Node getRoot() { return root; } // supplied

    public int sizeConstant() {
        return size;
    }
    public void clear() {
        root = null;
        size = 0;
    }
    public void addToHead(Orb data) {
        Node n = new Node(data);
        Node tmp = root;
        
        n.setNext(tmp);
        root = n;
        
        size++;
    }
    public void addToTail(Orb data) {        
        Node n = new Node(data);
        
        if(root == null) {
            root = n;
        } else {
            Node tmp = root;
            
            while(tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            
            tmp.setNext(n);
        }
        
        size++;
    }
    public void delete(int index) {
        Node tmp = root;
        
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        
        if(index == 0) {
            root = root.getNext();
        } else {
            for(int i = 0; i < index - 1; i++) {
                    tmp = tmp.getNext();
            }
            
            tmp.setNext(tmp.getNext().getNext());
        }
        
        size--;
    }

    public void move(int width, int height) {
        Node tmp = root;
        
        while(tmp != null) {
            tmp.getData().move(width, height);
            tmp = tmp.getNext();
        }
    }
    public void draw(Graphics g) {
        Node tmp = root;
        
        while(tmp != null) {
            if(!tmp.getData().isInactive()) {
                tmp.getData().draw(g);
            }
            
            tmp = tmp.getNext();
        }
    }
    
    
    public Target findTargetHitBy(Bullet bullet) {
        Node tmp = root;
        
        while(tmp != null) {
            Orb orb = tmp.getData();
            
            if (orb instanceof Target && !orb.isInactive() && bullet.collidesWith(orb)) {
            return (Target) orb;
        }
            tmp = tmp.getNext();
        }
        
        return null;
    }

    /** Return the number removed, keeping surviving Orb/Node identities and order. */
    public int removeInactive() {
        int counter = 0;
        
        while(root != null && root.getData().isInactive()) {
            root = root.getNext();
            counter++;
            size--;
        }
        
        Node tmp = root;
        
        while(tmp != null && tmp.getNext() != null) {
            
            if(tmp.getNext().getData().isInactive()) {
                tmp.setNext(tmp.getNext().getNext());
                counter++;
                size--;
            } else {
                tmp = tmp.getNext();
            }
        } 
        
        return counter;
    }

    /** Ascending x; selection sort; exchange Orb references, never individual attributes. */
    public void sortByX() {
        for(Node tmp = root; tmp != null; tmp = tmp.getNext()) {
            Node min = tmp;
            for(Node search = tmp.getNext(); search != null; search = search.getNext()) {
                if(search.getData().getX() < min.getData().getX()) {
                    min = search;
                }
            }
            Orb saved = tmp.getData();
            tmp.setData(min.getData());
            min.setData(saved);
        }
    }
}
