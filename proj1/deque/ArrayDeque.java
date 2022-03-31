package deque;



public class ArrayDeque<T> {
    private int size;
    private T[] items;
    public int nextFirst;
    public int nextLast;



    // ALL HELPER METHOD !!!!

    /**
     * check if the items variable need to be resized larger
     * otherwise we can not update the nextFirst or nextLast
     * value in this current array
     */
    private boolean NeedLarger() {
        if (nextFirst == 0 && nextLast == items.length - 1) {
            return true;
        }
        if (nextFirst > nextLast && nextFirst - nextLast == 1){
            return true;
        }
        return false;
    }

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

    private void resize(int capacity){
        T[] tempt = (T[]) new Object[capacity];
        int front = getFrontIndex();
        int back = getBackIndex();
        int current_length = items.length;
        if (front < back){
            System.arraycopy(items,front,tempt,0,size);
            items = tempt;
            nextFirst = items.length-1;
            nextLast = size;
            return;
        }
        System.arraycopy(items,front,tempt,0,current_length-front);
        System.arraycopy(items,0,tempt,current_length-front,back+1);
        items = tempt;
        nextFirst = items.length-1;
        nextLast = size;
        return;
    }

    /**update nextLast variable after we call addLast method
     * for the object whose items need NOT Enlarger !!!!
     */

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


    // START TO IMPLEMENT!!!!

    // create a empty arraydeque.
    public ArrayDeque() {
        items = (T[]) new Object[8];
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
        if (NeedLarger()){
            resize(2* items.length);
        }
        items[nextFirst] = item;
        updateNextFirst();
        size += 1;
    }

    public void addLast(T item){
        if (NeedLarger()){
            resize(2* items.length);
        }
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



}