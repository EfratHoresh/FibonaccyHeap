/**
 * FibonacciHeap
 *
 * An implementation of a Fibonacci Heap over integers.
 */
public class FibonacciHeap {

    public HeapNode first;
    public HeapNode min;
    public int numOfTrees;
    public int numOfMarkedNodes;
    public int size;

    public FibonacciHeap() {
        this.first = null;
        this.min = null;
        this.numOfTrees = 0;
        this.numOfMarkedNodes = 0;
        this.size = 0;
    }

    public HeapNode getFirst() {
        return this.first;
    }

    public int getNumOfTrees() {
        return this.numOfTrees;
    }

    public int getNumOfMarkedNodes() {
        return this.numOfMarkedNodes;
    }

    public void mark(HeapNode node) {
        if (!node.isMarked()) {
            node.mark = true;
            numOfMarkedNodes++;
        }
    }

    public void unmark(HeapNode node) {
        if (node.isMarked()) {
            node.mark = false;
            numOfMarkedNodes--;
        }
    }

    public void setAsRoot(HeapNode node) {
        this.unmark(node);
        node.parent = null;
    }


    /**
     * public boolean isEmpty()
     * <p>
     * Returns true if and only if the heap is empty.
     */
    public boolean isEmpty() {
        return this.getFirst()==null; // should be replaced by student code
    }

    /**
     * public HeapNode insert(int key)
     * <p>
     * Creates a node (of type HeapNode) which contains the given key, and inserts it into the heap.
     * The added key is assumed not to already belong to the heap.
     * <p>
     * Returns the newly created node.
     */
    public HeapNode insert(int key) {
        HeapNode addedNode = new HeapNode(key);
        if (this.isEmpty()) {
            this.first = addedNode;
            this.min = addedNode;
        }
        else {
            if (this.findMin().getKey()>key) {
                this.min = addedNode;
            }
            this.getFirst().insertPrev(addedNode);
            this.first = addedNode;
        }
        numOfTrees++;
        size++;
        return addedNode; // should be replaced by student code
    }

    /**
     * public void deleteMin()
     * <p>
     * Deletes the node containing the minimum key.
     */
    public void deleteMin() {
        if (this.isEmpty()) {
            return;
        }
        HeapNode oldMin = this.findMin();
        HeapNode prevMin = oldMin.getPrev();
        HeapNode nextMin = oldMin.getNext();
        HeapNode son = oldMin.getFirstChild();
        int minRank = oldMin.getRank();
        if (oldMin.equals(nextMin)) { //min is only root in heap
            this.first = son;
        }
        else {
            prevMin.next = nextMin;
            nextMin.prev = prevMin;
        }
        if (oldMin.equals(this.getFirst())) { // if min is first root in heap
            this.first = son;
        }
        if (son!=null) { // min is not a root without children
            do { // unmark min sons & make their parent null
                this.setAsRoot(son);
                son = son.next;
            }
            while (!son.equals(oldMin.getFirstChild())); // go over min sons
            insertTreesAfterNode(son, prevMin); // add sons as roots to heap
        }
        numOfTrees+=minRank-1;
        HeapNode newMin = this.getFirst(); // find new min
        HeapNode searchMin = this.getFirst();
        do {
            if (searchMin.getKey() < newMin.getKey()) {
                newMin = searchMin;
            }
            searchMin = searchMin.getNext();
        }
        while (!searchMin.equals(this.getFirst()));
        size--;
    }

    public void insertTreesAfterNode(HeapNode firstRoot, HeapNode node) {
        HeapNode nextNode = node.getNext();
        HeapNode lastRoot = firstRoot.getPrev();
        firstRoot.prev = node;
        node.next = firstRoot;
        lastRoot.next = nextNode;
        nextNode.prev = lastRoot;
    }

    /**
     * public HeapNode findMin()
     * <p>
     * Returns the node of the heap whose key is minimal, or null if the heap is empty.
     */
    public HeapNode findMin() {
        return this.min;// should be replaced by student code
    }

    /**
     * public void meld (FibonacciHeap heap2)
     * <p>
     * Melds heap2 with the current heap.
     */
    public void meld(FibonacciHeap heap2) {
        this.numOfTrees += heap2.numOfTrees;
        this.numOfMarkedNodes += heap2.numOfMarkedNodes;
        this.size+=heap2.size();
        if (heap2.findMin().getKey() < this.findMin().getKey()) {
            this.min = heap2.findMin();
        }
        insertTreesAfterNode(heap2.getFirst(), this.getFirst().getPrev());
    }

    /**
     * public int size()
     * <p>
     * Returns the number of elements in the heap.
     */
    public int size() {
        return this.size; // should be replaced by student code
    }

    /**
     * public int[] countersRep()
     * <p>
     * Return an array of counters. The i-th entry contains the number of trees of order i in the heap.
     * Note: The size of of the array depends on the maximum order of a tree, and an empty heap returns an empty array.
     */
    public int[] countersRep() {
        if (this.isEmpty()) {
            return new int[0];
        }
        int maxRankInHeap = 0;
        HeapNode rootNode = this.getFirst();
        do { // find max rank in heap
            if (rootNode.rank > maxRankInHeap) {
                maxRankInHeap = rootNode.rank;
            }
            rootNode = rootNode.getNext();
        }
        while (!rootNode.equals(this.getFirst()));
        int[] arr = new int[maxRankInHeap];
        do { // iterate through all roots and update array
            arr[rootNode.getRank()]++;
            rootNode = rootNode.getNext();
        }
        while (!rootNode.equals(this.getFirst()));
        return arr; //	 to be replaced by student code
    }

    /**
     * public void delete(HeapNode x)
     * <p>
     * Deletes the node x from the heap.
     * It is assumed that x indeed belongs to the heap.
     */
    public void delete(HeapNode x) {
        return; // should be replaced by student code
    }

    /**
     * public void decreaseKey(HeapNode x, int delta)
     * <p>
     * Decreases the key of the node x by a non-negative value delta. The structure of the heap should be updated
     * to reflect this change (for example, the cascading cuts procedure should be applied if needed).
     */
    public void decreaseKey(HeapNode x, int delta) {
        return; // should be replaced by student code
    }

    /**
     * public int potential()
     * <p>
     * This function returns the current potential of the heap, which is:
     * Potential = #trees + 2*#marked
     * <p>
     * In words: The potential equals to the number of trees in the heap
     * plus twice the number of marked nodes in the heap.
     */
    public int potential() {
        return -234; // should be replaced by student code
    }

    /**
     * public static int totalLinks()
     * <p>
     * This static function returns the total number of link operations made during the
     * run-time of the program. A link operation is the operation which gets as input two
     * trees of the same rank, and generates a tree of rank bigger by one, by hanging the
     * tree which has larger value in its root under the other tree.
     */
    public static int totalLinks() {
        return -345; // should be replaced by student code
    }

    /**
     * public static int totalCuts()
     * <p>
     * This static function returns the total number of cut operations made during the
     * run-time of the program. A cut operation is the operation which disconnects a subtree
     * from its parent (during decreaseKey/delete methods).
     */
    public static int totalCuts() {
        return -456; // should be replaced by student code
    }

    /**
     * public static int[] kMin(FibonacciHeap H, int k)
     * <p>
     * This static function returns the k smallest elements in a Fibonacci heap that contains a single tree.
     * The function should run in O(k*deg(H)). (deg(H) is the degree of the only tree in H.)
     * <p>
     * ###CRITICAL### : you are NOT allowed to change H.
     */
    public static int[] kMin(FibonacciHeap H, int k) {
        int[] arr = new int[100];
        return arr; // should be replaced by student code
    }

    /**
     * public class HeapNode
     * <p>
     * If you wish to implement classes other than FibonacciHeap
     * (for example HeapNode), do it in this file, not in another file.
     */
    public static class HeapNode {

        public int key;
        public int rank;
        public int size;
        public boolean mark;
        public HeapNode child;
        public HeapNode next;
        public HeapNode prev;
        public HeapNode parent;


        public HeapNode(int key) {
            this.key = key;
            this.rank = 0;
            this.size = 1;
            this.mark = false;
            this.child = null;
            this.next = this;
            this.prev = this;
            this.parent = null;
        }


        public int getKey() {
            return this.key;
        }

        public int getRank() {
            return this.rank;
        }

        public int getSize() {
            return this.size;
        }

        public boolean isMarked() {
            return this.mark;
        }

        public HeapNode getFirstChild() {
            return this.child;
        }

        public HeapNode getNext() {
            return this.next;
        }

        public HeapNode getPrev() {
            return this.prev;
        }

        public HeapNode getParent() {
            return this.parent;
        }

        public boolean isRoot() {
            return this.getParent()==null;
        }

        public void insertNext(HeapNode addedNode) {
            HeapNode nextNode = this.getNext();
            addedNode.prev = this;
            addedNode.next = nextNode;
            this.next = addedNode;
            nextNode.prev = addedNode;
        }

        public void insertPrev(HeapNode addedNode) {
            this.getPrev().insertNext(addedNode);
        }

        public boolean equals(HeapNode other) {
            if (this==null && other==null) {
                return true;
            }
            else if (this==null || other==null) {
                return false;
            }
            return this.getKey()== other.getKey();
        }

    }
}