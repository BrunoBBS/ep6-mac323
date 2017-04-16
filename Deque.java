import edu.princeton.cs.algs4.*;
import java.util.*;

public class Deque<Item> implements Iterable<Item>{
    //For a colorful unit testing
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    Node first, last;
    public static int nodeNumber;

    private class Node {
        Item val;
        Node prev;
        Node next;
        public Node(Item val, Node prev, Node next) {
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
        Node newNode = new Node(item, null, first);
        first = newNode;
        if (nodeNumber == 0)
            last = newNode;
        nodeNumber++;
    }

    public void addLast(Item item) {
        Node newNode = new Node(item, last, null);
        if (last != null)
            last.next = newNode;
        last = newNode;
        nodeNumber++;
    }

    public Item removeFirst() {
        if (first == null)
            throw new NoSuchElementException("First element does not exist.");
        Item ret = first.val;
        if (first.next != null)
            first.next.prev = null;
        first = first.next;
        nodeNumber--;
        return ret;

    }

    public Item removeLast() {
        if (last == null)
            throw new NoSuchElementException("Last element does not exist.");
        Item ret = last.val;
        last = last.prev;
        last.next = null;
        nodeNumber--;
        return ret;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        Node cursor = first;
        public boolean hasNext() {
            return cursor != null;
        }
        public Item next() {
            if (cursor == null) throw new NoSuchElementException("There is no next element.");
            Node tmp =  cursor;
            cursor = cursor.next;
            return tmp.val;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        StdOut.print("The newly created Deque must be empty................");
        if (d.isEmpty()) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("The number of items in it must be 0..................");
        if (d.size() == 0)StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        d.addFirst(1);
        d.addLast(5);
        StdOut.print("Now the Deque must have two items....................");
        if (!d.isEmpty() && d.size() == 2) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("Testing if the removeFirst returns correctly.........");
        if (d.removeFirst() == 1) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("Testing if the removeFirst returns correctly again...");
        if (d.removeFirst() == 5) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("Now the number of items in it must be 0..............");
        if (d.size() == 0 && d.isEmpty())StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        d.addFirst(6);
        d.addLast(7);
        d.addFirst(5);
        d.addLast(8);
        d.addFirst(4);
        d.addLast(9);
        d.addFirst(3);
        d.addLast(10);
        d.addFirst(2);
        d.addLast(11);
        d.addFirst(1);
        d.addLast(12);
        StdOut.print("Now the Deque should have 12 items...................");
        if (!d.isEmpty() && d.size() == 12) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        int count = 0;
        for (Integer item : d) {
            count++;
        }
        StdOut.print("Iterator test........................................");
        if (count == 12) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("Testing removeLast...................................");
        if (d.removeLast() == 12) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("Testing removeLast again.............................");
        if (d.removeLast() == 11) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);

    }

}
