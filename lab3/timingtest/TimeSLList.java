package timingtest;
import edu.princeton.cs.algs4.Stopwatch;
import org.checkerframework.checker.units.qual.A;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        int N =1000;
        for(int i =1; i<=8; i=i+1){
            Ns.addLast(N);
            N = N * 2;
        }
        AList<Double> times = new AList<>();
        SLList<Integer> L = new SLList<>();
        for(int j=0; j<8; j=j+1){
            for(int k = 0; k < Ns.get(j); k=k+1){
                L.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for(int m =0; m<10000;m=m+1){
                L.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }
        AList<Integer> M = new AList<>();
        for(int iterator=0; iterator<8;iterator=iterator+1){
            M.addLast(10000);
        }
        printTimingTable(Ns,times,M);

    }

}
