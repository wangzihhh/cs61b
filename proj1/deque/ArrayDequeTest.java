package deque;

import static org.junit.Assert.*;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;
import java.util.Optional;

public class ArrayDequeTest {

    public static class MaxInt implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) {
                return 1;
            } else if (o1 == o2) {
                return 0;
            }
            return -1;
        }


        @Test
        public void SizeTest() {
            ArrayDeque<Integer> a = new ArrayDeque<>();
            for (int i = 0; i < 10; i += 1) {
                a.addFirst(i);
            }
            assertEquals(a.size(), 10);
        }

        @Test
        public void GetTest() {
            ArrayDeque<Integer> a = new ArrayDeque<>();
            for (int i = 0; i < 81; i += 1) {
                a.addFirst(i);
            }
            int test = a.get(0);
            assertEquals(test, 80);
            int last = a.removeLast();
            assertEquals(last, 0);
        }

        public static void main(String[] args) {
            ArrayDeque<Integer> l1 = new ArrayDeque<>();
            ArrayDeque<Integer> l2 = new ArrayDeque<>();
            for (int i = 0; i < 10000; i += 1) {
                if (i < 5200) {
                    l1.addLast(i);
                    l2.addLast(i);
                }
                if (i < 10000) {
                    l1.removeLast();
                    l2.removeLast();
                }
            }
            System.out.println(l1.equals(l2));
        }

        @Test
        public void randomizedTest() {
            ArrayDeque<Integer> test = new ArrayDeque<>();
            ArrayDequeNoResize<Integer> correct = new ArrayDequeNoResize<>();
            int N = 9000;
            for (int i = 0; i < N; i += 1) {
                int operationNumber = StdRandom.uniform(0, 4);
                if (operationNumber == 0) {
                    int randVal = StdRandom.uniform(0, 100);
                    test.addFirst(randVal);
                    correct.addFirst(randVal);
                }
                if (operationNumber == 1) {
                    assertEquals(correct.size(), test.size());
                }

                if (operationNumber == 2) {
                    if (correct.size() == 0) {
                        continue;
                    }
                    assertEquals(correct.get(0), test.get(0));
                }
                if (operationNumber == 3) {
                    if (correct.size() == 0) {
                        continue;
                    }
                    assertEquals(correct.removeFirst(), test.removeFirst());
                }
            }
        }

        @Test
        public void MaxArrayTest() {
            Comparator<Integer> cmp = new MaxInt();
            MaxArrayDeque<Integer> a = new MaxArrayDeque<>(cmp);
            a.addFirst(1);
            a.addFirst(2);
            a.addFirst(3);
            a.addFirst(10);
            int test = a.max();
            assertEquals(10, test);
        }

        @Test
        public void RemoveFirstTest(){
            ArrayDeque<Integer> a = new ArrayDeque<>();
            for (int i = 0; i < 100000; i += 1){
                a.addFirst(i);
            }
            for (int j = 0; j < 10000; j += 1){
                a.removeLast();
            }
            int removeelement = a.removeFirst();
            assertEquals(99999,removeelement);
        }

    }
}