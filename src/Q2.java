public class Q2 {
    public static void main(String[] args) {
        int exp = 14;
        int m = (int)Math.pow(3, exp)-1;
//        int m = 12;
        FibonacciHeap heap = new FibonacciHeap();
        long start = System.currentTimeMillis();
        for (int i=0; i<=m; i++) {
            heap.insert(i);
        }
        int limit = 3*m/4;
        for (int i=1; i<=limit; i++) {
            heap.deleteMin();
        }
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("m = " + m);
        System.out.println("time = " + time);
        System.out.println("totalLinks = " + FibonacciHeap.totalLinks());
        System.out.println("totalCuts = " + FibonacciHeap.totalCuts());
        System.out.println("potential = " + heap.potential());

    }
}
