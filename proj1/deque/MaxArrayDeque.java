package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c){
        cmp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T currentmax = get(0);
        for (int i = 0; i < size(); i += 1) {
            T tempt = get(i);
            if (cmp.compare(tempt, currentmax) > 0){
                currentmax = tempt;
            }
        }
        return currentmax;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T currentmax = get(0);
        for (int i = 0; i < size(); i += 1) {
            T tempt = get(0);
            if (c.compare(tempt, currentmax) > 0) {
                currentmax = tempt;
            }
        }
        return currentmax;
    }

}
