package sylvia.util;

/**
 * Represents a queue that allows for random access to elements. The queue
 * accepts non-null elements. The queue has a default maximum capacity of 100.
 * New elements are added to the tail of the queue. When the queue is full,
 * adding new elements will remove the oldest elements from the head of the
 * queue.
 *
 * @param <E> the type of elements in the queue
 */
public class RandomAccessQueue<E> {
    // TODO: Implement this class to have the same behavior as a Queue, but with the
    // ability to access elements at any index.
    private static final int MAX_CAPACITY = 100;

    /**
     * The array to store elements in the queue.
     */
    private E[] elements;

    /**
     * The number of elements in the queue.
     */
    private int size;

    /**
     * The index of the head of the queue. It is the index of the first element in
     * the queue.
     */
    private int head;

    /**
     * The index of the tail of the queue. It is the index where the next element
     * will be added to the queue.
     */
    private int tail;

    /**
     * The index of the current element for traversal in the queue. By default, it
     * is at the tail of the queue.
     */
    private int cur;

    /**
     * The maximum capacity of the queue.
     */
    private int capacity;

    /**
     * Constructs a new random access queue with a default maximum capacity of 100.
     */
    public RandomAccessQueue() {
        // Suppress unchecked cast warning
        capacity = MAX_CAPACITY;
        @SuppressWarnings("unchecked")
        E[] a = (E[]) new Object[capacity];
        elements = a;
        size = 0;
        head = 0;
        tail = 0;
        cur = 0;
    }

    /**
     * Constructs a new random access queue with the specified maximum capacity.
     *
     * @param capacity the maximum capacity of the queue
     * @throws IllegalArgumentException if the specified capacity is less than 1
     */
    public RandomAccessQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1");
        }
        // Suppress unchecked cast warning
        @SuppressWarnings("unchecked")
        E[] a = (E[]) new Object[capacity];
        elements = a;
        size = 0;
        head = 0;
        tail = 0;
        cur = 0;
        this.capacity = capacity;
    }

    /**
     * Adds the specified element to the tail of the queue. If the queue is full,
     * the oldest element will be removed from the head of the queue.
     *
     * @param e the element to add
     * @throws IllegalStateException if the queue is full
     */
    public void addLast(E e) {
        if (size == capacity) {
            pollFirst();
        }
        elements[tail] = e;
        tail = (tail + 1) % capacity;
        size++;
        // reset the current traversal index
        cur = tail;
    }

    /**
     * Removes the first element from the queue and returns it.
     *
     * @return the first element in the queue, or null if the queue is empty
     */
    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        E e = elements[head];
        head = (head + 1) % capacity;
        size--;
        return e;
    }

    /**
     * Returns the element at the specified index in the queue.
     *
     * @param index the index of the element to return
     * @return the element at the specified index
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[(head + index) % capacity];
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Traverses up towards the head to the next element in the queue and returns
     * it. If the head of the queue is reached, returns null.
     *
     * @return the next element in the queue, or null if the head of the queue is
     *         reached
     */
    public E traverseUp() {
        if (cur == head) {
            return null;
        }
        cur = (cur - 1 + capacity) % capacity;
        return elements[cur];
    }

    /**
     * Traverses down towards the tail to the next element in the queue and returns
     * it. If the tail of the queue is reached, returns null.
     *
     * @return the next element in the queue, or null if the tail of the queue is
     *         reached
     */
    public E traverseDown() {
        if (cur == tail) {
            return null;
        }
        cur = (cur + 1) % capacity;
        E e = elements[cur];
        return e;
    }

    /**
     * Returns whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
