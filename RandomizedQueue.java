import edu.princeton.cs.algs4.*;
import java.util.*;

class RandomizedQueue<Item> implements Iterable<Item> {
    //For a colorful unit testing
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    @SuppressWarnings("unchecked")
    private Item[] rQueue = (Item[]) new Object[2];
    private int nItems = 0;

    public boolean isEmpty() {
        return nItems == 0;
    }
    public int size() {
        return nItems;
    }
    public void enqueue(Item item) {
        if (rQueue.length == nItems)
            resize(2*rQueue.length);
        rQueue[nItems] = item;
        nItems++;
    }
    public Item dequeue() {
        int place = StdRandom.uniform(nItems);
        Item tmp = rQueue[place];
        rQueue[place] = rQueue[--nItems];
        rQueue[nItems] = null;
        if (nItems <= rQueue.length/4)
            resize(rQueue.length/2);
        return tmp;
    }
    public Item sample() {
        int place = StdRandom.uniform(nItems);
        return rQueue[place];
    }
    public Iterator<Item> iterator() {
        return new randomIterable();
    }

    private class randomIterable implements Iterator<Item> {
        RandomizedQueue<Item> rq = new RandomizedQueue<Item>();
        int iteredItems = 0;
        public  randomIterable() {
            for (int i = 0; i < nItems; i++) {
                rq.enqueue(rQueue[i]);
            }
        }
        public boolean hasNext() {
            return !rq.isEmpty();
        }
        public Item next() {
            iteredItems++;
            return rq.dequeue();
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    };

    @SuppressWarnings("unchecked")
    private void resize(int nSize) {
        Item[] tmp = (Item[]) new Object[nSize];
        for (int i = 0; i < nItems; i++) {
            tmp[i] = rQueue[i];
        }
        rQueue = tmp;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        StdOut.print("The newly created Randomized Queue  must be empty...");
        if (rq.isEmpty()) StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("The number of items in it must be 0.................");
        if (rq.size() == 0)StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        rq.enqueue(5);
        StdOut.print("The number of items in it must be 1.................");
        if (rq.size() == 1)StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("Testing sample......................................");
        if (rq.sample() == 5)StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("The number of items in it should still be 1.........");
        if (rq.size() == 1)StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("Testing dequeue.....................................");
        if (rq.dequeue() == 5)StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        StdOut.print("The rq should be empty..............................");
        if (rq.size() == 0 && rq.isEmpty())StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        StdOut.print("Now it should have 6 items..........................");
        if (rq.size() == 6)StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        int count = 0;
        int[] res1 = new int[6];
        int[] res2 = new int[6];
        for (Integer item : rq) {
            res1[count] = item;
            count++;
        }
        StdOut.print("Testing iterator....................................");
        if (count == 6)StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
        count = 0;
        for (Integer item : rq) {
            res2[count] = item;
        }
        StdOut.print("Testing randomness..................................");
        if (!res1.equals(res2))StdOut.println(ANSI_GREEN + "OK" + ANSI_RESET);
        else StdOut.println(ANSI_RED + "FAILED!" + ANSI_RESET);
    }

}


