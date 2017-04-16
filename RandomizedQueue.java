import edu.princeton.cs.algs4.*;
import java.util.*;

class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rQueue = (Item[]) new Object[1];
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
        rQueue[nItems++] = item;
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
            return rq.isEmpty();
        }
        public Item next() {
            iteredItems++;
            return rq.dequeue();
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    };
    public static void main(String[] args) {

    }

    private void resize(int nSize) {
        Item[] tmp = (Item[]) new Object[nSize];
        for (int i = 0; i < nItems; i++) {
            tmp[i] = rQueue[i];
        }
        rQueue = tmp;
    }
}


