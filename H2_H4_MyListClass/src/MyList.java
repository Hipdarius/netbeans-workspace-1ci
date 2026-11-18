
public class MyList {

    private Node root = null;
    private int size;

    // Time: O(n), Space: O(1)
    public void add(int number) {
        Node n = new Node(number);
        if (root == null) {
            root = n;
        } else {
            Node tmp = root;
            while(tmp.getNext()!=null) {
                tmp = tmp.getNext();
            }
            tmp.setNext(n);
        } 
        size++;
    }
    
    // Time: O(n), Space: O(1)
    public void print() {
        Node tmp = root;
        
        if(tmp != null) {
            while(tmp != null) {
                System.out.println(tmp.getData());
                tmp = tmp.getNext();
            }
        }
    }
    
    // Time: O(n), Space: O(1)
    public void delete(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException(index);
        
        if(index > 0) {
            Node tmp = root;

            for(int i = 0; i < index - 1; i++) {
                tmp = tmp.getNext();
                //remove node from chain
                if(tmp == null)
                            throw new IndexOutOfBoundsException(index);
            } 
            
            tmp.setNext(tmp.getNext().getNext());
        } else {
            root = root.getNext();
        }
        
        size--;
    }   

    
    // Time: O(n), Space: O(1)
    public int size() {
        Node tmp = root;
        int count = 0;
        
        while(tmp != null) {
            tmp = tmp.getNext();
            count++;
        }
        
        return count;
    }
    
    // Time: O(1), Space: O(1)
    public int sizeConstant() {
        return size;
    }
    
    // Time: O(1), Space: O(1)
    public void clear() {
        root = null;
        size = 0;
    }
    
    // Time: O(n), Space: O(1)
    public int get(int index) {
        if(index < 0 || index >= size()) throw new IndexOutOfBoundsException(index);
        
        Node tmp = root;
        
        for(int i = 0; i < index; i++) {
            tmp = tmp.getNext();
            if(tmp == null) {
                throw new IndexOutOfBoundsException(index);
            }
        }
        
        return tmp.getData();
    }
    
    // Time: O(n), Space: O(1)
    public void replace(int index, int data) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException(index);
        Node tmp = root;
        
        for(int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        
        tmp.setData(data);
    }
    
    // Time: O(1), Space: O(1)
    public void addToHead(int data) {
        Node n = new Node(data);
        
        n.setNext(root);
        root = n;
        
        size++;
    }
    
    // Time: O(n), Space: O(1)
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
        Node tmp = root;

        while (tmp != null) {
            if (tmp.getData() == data) {
                return true;
            }

            tmp = tmp.getNext();
        }

        return false;
        
        //return indexOf(data) != -1;
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
    
    // Time: O(n), Space: O(1)
    public void insert(int index, int data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index);
        }

        Node n = new Node(data);

        if (index == 0) {
            addToHead(data);
        } else {
            Node tmp = root;

            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.getNext();
            }

            n.setNext(tmp.getNext());
            tmp.setNext(n);
            
            size++;
        }
    }

    
    /*public static void main(String[] args) {
        MyList ml = new MyList();
        ml.add(10);
        ml.add(9);
        ml.add(17);
        ml.add(20);
        ml.add(5);
        
        ml.delete(2);
        
        ml.print();
        
        System.out.println();
        System.out.println("G: " + ml.get(3));
        
        System.out.println();
        System.out.println("S: " + ml.size());
    }*/
}
