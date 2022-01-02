public class Q1 {
    public static void main(String[] args) {
        int exp = 10;
        int m = (int)Math.pow(2, exp);
        FibonacciHeap heap = new FibonacciHeap();
        FibonacciHeap.HeapNode[] arr = new FibonacciHeap.HeapNode[m];
        long start = System.currentTimeMillis();
        for (int i=m-1; i>-1; i--) {
            arr[i] = heap.insert(i);
        }
        heap.insert(-1);
        heap.deleteMin();
        for (int i=exp; i>0; i--) {
            FibonacciHeap.HeapNode decrease = arr[m-(int)Math.pow(2, i)+1];
            heap.decreaseKey(decrease, m+1);
        }
        FibonacciHeap.HeapNode decreaseAgain = arr[m-2];
        heap.decreaseKey(decreaseAgain, m+1);
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("time = " + time);
        System.out.println("totalLinks = " + FibonacciHeap.totalLinks());
        System.out.println("totalCuts = " + FibonacciHeap.totalCuts());
        System.out.println("potential = " + heap.potential());

    }
}
