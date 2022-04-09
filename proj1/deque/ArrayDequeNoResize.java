package deque;

import java.util.Iterator;

public class ArrayDequeNoResize<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    private int getFrontIndex(){
        if (nextFirst == items.length-1){
            return 0;
        }
        return nextFirst+1;
    }

    private int getBackIndex(){
        if (nextLast == 0){
            return items.length-1;
        }
        return nextLast-1;
    }

    private void updateNextLast(){
        if (nextLast == items.length-1){
            nextLast = 0;
        }else {
            nextLast += 1;
        }
    }

    private void updateNextFirst(){
        if (nextFirst == 0){
            nextFirst = items.length - 1;
        }else{
            nextFirst -= 1;
        }
    }


    public ArrayDequeNoResize() {
        items = (T[]) new Object[10000];
        size = 0;
        nextLast = 5;
        nextFirst = 4;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        if (size() == 0){
            return true;
        }
        return false;
    }

    public void addFirst(T item){
        items[nextFirst] = item;
        updateNextFirst();
        size += 1;
    }

    public void addLast(T item){
        items[nextLast] = item;
        updateNextLast();
        size += 1;
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        int front = getFrontIndex();
        T x = items[front];
        items[front] = null;
        nextFirst = front;
        size -= 1;
        return x;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        int back = getBackIndex();
        T x = items[back];
        items[back] = null;
        nextLast = back;
        size -= 1;
        return x;
    }

    public T get(int index){
        if (isEmpty()){
            return null;
        }
        int front = getFrontIndex();
        int back = getBackIndex();
        if (index > size-1 | index < -1){
            return null;
        }
        if (front < back){
            return items[front+index];
        }
        if (index < items.length-front){
            return items[front+index];
        }
        return items[index-(items.length-front)];
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{

        private int wisPos;

        public ArrayDequeIterator(){
            wisPos = 0;
        }

        @Override
        public boolean hasNext(){
            return wisPos < size;
        }

        @Override
        public T next(){
            T returnItem = get(wisPos);
            wisPos += 1;
            return returnItem;
        }
    }





}
