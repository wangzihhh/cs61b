package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private  class Dnode {
        public T item;
        public Dnode prev;
        public Dnode next;

        //constructor of Dnode
        public Dnode(Dnode p, T i, Dnode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private Dnode sentinel;



    // create empty Deque
    public LinkedListDeque() {
        sentinel = new Dnode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        size += 1;
        sentinel.next.prev = new Dnode(sentinel, x, sentinel.next);
        sentinel.next = sentinel.next.prev;
    }

    // add a element in the last of list.
    public void addLast(T x) {
        size += 1;
        sentinel.prev.next = new Dnode(sentinel.prev, x, sentinel);
        sentinel.prev = sentinel.prev.next;
    }



    //return the size of the list.
    public int size() {
        return size;
    }

    // remove and return the first item from the list.
    public T removeFirst() {
        // handle for the base case: empty list
        if (this.isEmpty()) {
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
    public T removeLast() {
        // handle the base case.
        if (this.isEmpty()) {
            return null;
        }
        // handle for the none trivial case, this time do not consider the empty situation.
        size -= 1;
        T target = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return target;
    }

    public T get(int index) {
        if (isEmpty() == true) {
            return null;
        }
        if (index > size() - 1) {
            return null;
        }
        Dnode actualDeque = sentinel;
        for (int i = 0; i <= index; i += 1) {
            actualDeque = actualDeque.next;
        }
        return actualDeque.item;
    }

    public void printDeque() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }


    public T getRecursive(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index >= size() | index < 0) {
            return null;
        }
        if (index == 0){
            return sentinel.next.item;
        }
        LinkedListDeque<T> tempt = new LinkedListDeque<>();
        tempt.sentinel.next = sentinel.next.next;
        tempt.size = size() - 1;
        return tempt.getRecursive(index-1);
    }

    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private int wizPos;

        public LinkedListDequeIterator(){
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            if (wizPos < size){
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!((o instanceof ArrayDeque) || (o instanceof LinkedListDeque))){
            return false;
        }
        if (o instanceof ArrayDeque){
            ArrayDeque<T> a = (ArrayDeque<T>) o;
            for (int i = 0; i < size; i += 1){
                if (! this.get(i).equals(a.get(i))){
                    return false;
                }
            }
            return true;
        }
        LinkedListDeque<T> l = (LinkedListDeque<T>) o;
        for (int j = 0; j < size; j += 1){
            if (!this.get(j).equals(l.get(j))){
                return false;
            }
        }
        return true;
    }
}
