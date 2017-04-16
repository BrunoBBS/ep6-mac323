import java.lang.*;
import edu.princeton.cs.algs4.*;

public class Subset {
    private static RandomizedQueue<String> rq;

    public static void main(String[] args) {
        if (args.length == 0)
            System.out.println("Use: java Subset <number>");
        else {
            rq = new RandomizedQueue<String>();
            while (!StdIn.isEmpty()) {
                rq.enqueue(StdIn.readString());
            }
            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                StdOut.println(rq.dequeue());
            }
        }
    }
}
