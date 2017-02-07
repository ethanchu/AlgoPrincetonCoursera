
/**
 * Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure. Create a generic data type RandomizedQueue that implements the following API:

public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()                 // construct an empty randomized queue
   public boolean isEmpty()                 // is the queue empty?
   public int size()                        // return the number of items on the queue
   public void enqueue(Item item)           // add the item
   public Item dequeue()                    // remove and return a random item
   public Item sample()                     // return (but do not remove) a random item
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing (optional)
}
 * 
 * @author Yichen Zhu
 * @version 0.1
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] queue;
    private int last;
    
 /*   public RandomizedQueue(int capacity) {
        last = 0;
        queue = (Item[]) new Object[capacity];
    }*/
    
    public RandomizedQueue() {
        last = 0;
        queue = (Item[]) new Object[2];
    }   
    
    public boolean isEmpty() {
        return (last==0);
    }
    
    public int size()  {
        return last;
    }
    
    private void resize(int capacity) {
        Item[] tmp = (Item[]) new Object[capacity];
        for (int i = 0; i< size(); i++) {
            tmp[i] = queue[i];
        }
        queue = tmp;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (last == queue.length) {
            resize(2*last);
        }
        queue[last++] = item;        
    }
    
    public Item dequeue()  {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(last);
        Item item = queue[random];
        queue[random] = queue[last-1];
        queue[last-1] = null;
        last --;
        if (last > 0  && last == queue.length/4) {
            resize(queue.length/2);
        }
        return item;
    }
    
    public Item sample()  {
       if (isEmpty()) throw new NoSuchElementException(); 
       int random = StdRandom.uniform(last);
       Item item = queue[random];
       return item;
    }
    
   public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
       return new RandomizedQueueIterator();
    }
    
   private class RandomizedQueueIterator implements Iterator<Item> {
       
       private int Clast;
       private Item[] Cqueue;
       
       public RandomizedQueueIterator() {
           Clast = last;
           Cqueue = (Item[]) new Object[Clast];
           for (int i = 0; i< Clast; i++) {
               Cqueue[i] = queue[i];
            }
        }
       
       public boolean hasNext(){
         return Clast != 0;
        }
        
       public void remove() {throw new UnsupportedOperationException();}
       
       public Item next(){
           if (Clast == 0) {throw new NoSuchElementException();}
           int random = StdRandom.uniform(Clast);
           Item item  = Cqueue[random];
           Cqueue[random] = Cqueue[Clast-1];
           Cqueue[Clast-1] = null;
           Clast --;
           return item;
        }
       
    }
    public static void main(String[] args) {  // unit testing (optional)
    }
}
