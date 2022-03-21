package deque;

public class LinkedListDeque<T> {

    private class Dnode{
        public T item;
        public Dnode prev;
        public Dnode next;

        //constructor of Dnode
        public Dnode(Dnode p, T i, Dnode n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private Dnode sentinel;

    // create empty Deque
    public LinkedListDeque(){
        sentinel = new Dnode(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T x){
        size += 1;
        sentinel.next.prev = new Dnode(sentinel, x, sentinel.next);
        sentinel.next = sentinel.next.prev;
    }

    // add a element in the last of list.
    public void addLast(T x){
        size += 1;
        sentinel.prev.next = new Dnode(sentinel.prev, x, sentinel);
        sentinel.prev = sentinel.prev.next;
    }

    // return whether a list is empty
    public boolean isEmpty(){
        if (sentinel.next == sentinel){
            return true;
        }
        return false;
    }

    //return the size of the list.
    public int size(){
        return size;
    }

    // remove and return the first item from the list.
    public T removeFirst(){
        // handle for the base case: empty list
        if (this.isEmpty()){
            return null;
        }
        // handle for the none trivial case, this time do not consider empty situation.
        size -= 1;
        T target = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return target;
    }

    //remove and return the last item from the list.
    public T removeLast(){
        // handle the base case.
        if (this.isEmpty()){
            return null;
        }
        // handle for the none trivial case, this time do not consider the empty situation.
        size -= 1;
        T target = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return target;
    }
    

}
