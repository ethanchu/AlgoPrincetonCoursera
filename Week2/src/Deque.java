
/**
 * Write a description of class Deque here.
 * 
 * @author Yichen Zhu
 * @version 0.1: initial start
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>
{

   private Node first;
   private Node last;
   private int count;
   
   private class Node  {
       public Item item;
       public Node next;
       public Node previous;
       
    }
    
   public Deque() {                          // construct an empty deque
      first = null;
      last = null;
      count = 0;
    }
       
   public boolean isEmpty() {                // is the deque empty?
       if (count == 0) return true;
       return false;
    }
    
   public int size() {                       // return the number of items on the deque
       return count;
    }
    
   public void addFirst(Item item)  {        // add the item to the front
       if (item == null) throw new java.lang.NullPointerException();
           Node oldfirst = first;
           first = new Node();
           first.item = item;
           first.next = oldfirst;
           first.previous = null;
        if (oldfirst != null) {
           oldfirst.previous = first; 
        } 
       else {
           last = first;
        }
       count ++;
       
    }
   public void addLast(Item item)  {         // add the item to the end
       if (item == null) throw new java.lang.NullPointerException();
       Node oldlast = last;
       last = new Node();
       last.item = item;
       last.previous = oldlast;
       last.next = null;
        if (oldlast != null) {
            oldlast.next = last;
        }
        else {
           first = last; 
        }
       count ++;
      
    }
    
   public Item removeFirst() {               // remove and return the item from the front
       if (isEmpty()) throw new NoSuchElementException("Deque is empty");
       Item item = first.item;
       first = first.next;
        if (first != null){
            first.previous = null;
        }
        else {
            last = null;
        }
       count --;
       return item;

    }
   public Item removeLast()   {              // remove and return the item from the end
       if (isEmpty()) throw new NoSuchElementException("Deque is empty");
       Item item  = last.item;
       last = last.previous;
        if (last != null){
            last.next = null;
        }
        else {
            first = null;
        }       
       count --;  
       return item;
  
    }
   public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
       return new DequeIterator();
    }
    
   private class DequeIterator implements Iterator<Item> {
       
       private Node current = first;
       
       public boolean hasNext(){
         return current != null;
        }
        
       public void remove() {throw new UnsupportedOperationException();}
       
       public Item next(){
           if (current == null) {throw new NoSuchElementException();}
           Item item = current.item;
           current = current.next;
           return item;
        }
       
    }

    
   public static void main(String[] args) {  // unit testing (optional)
   }
    
}
