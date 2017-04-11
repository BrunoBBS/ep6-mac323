import edu.princeton.cs.algs4.*;
import java.util.*;

public class Deque<Item> implements Iterable<Item>{
    Node first, last;
    public static int nodeNumber;

    private class Node {
        Item val;
        Node next;
        Node prev;
        public Node(Item val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public Deque() {
        first = null;
        last = first;
        nodeNumber = 0;
    }

    public boolean isEmpty() {
        return nodeNumber == 0;
    }

    public int size() {
        return nodeNumber;
    }

    public void addFirst(Item item) {
        Node newNode = new Node(item, first, null);
        first = newNode;
    }

    public void addLast(Item item) {
        Node newNode = new Node(item, null, last);
        last.next = newNode;
        last = newNode;
    }

    public Item removeFirst() {
        if (first == null)
            throw new NoSuchElementException("First element does not exist.");
        Item ret = first.val;
        first.next.prev = null;
        first = first.next;
        return ret;

    }

    public Item removeLast() {
        if (last == null)
            throw new NoSuchElementException("Last element does not exist.");
        Item ret = last.val;
        last = last.prev;
        last.next = null;
        return ret;

    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        Node cursor = first;
        public boolean hasNext() {
            return cursor.next != null;
        }

        public Item next() {
            Node tmp =  cursor;
            cursor = cursor.next;
            return tmp.val;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
    }

}
