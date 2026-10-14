package storage;

import model.FoodItem;
import exception.StorageEmptyException;
import exception.StorageFullException;

// Fixed-size circular deque: items can be added/removed at both ends in O(1)
public class FoodStorage {

    public static final int MAX_CAPACITY = 8;
    private final FoodItem[] items = new FoodItem[MAX_CAPACITY];
    private int size = 0;   // how many items are stored
    private int head = 0;   // index of the front item

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == MAX_CAPACITY;
    }

    public int size() {
        return size;
    }

    // Add at the rear. O(1)
    public void addRear(FoodItem item) {
        if (isFull()) {
            throw new StorageFullException(MAX_CAPACITY);
        }
        items[(head + size) % MAX_CAPACITY] = item;
        size++;
    }

    // Remove from the front. O(1)
    public FoodItem removeFront() {
        if (isEmpty()) {
            throw new StorageEmptyException(MAX_CAPACITY);
        }
        FoodItem removed = items[head];
        items[head] = null;
        head = (head + 1) % MAX_CAPACITY;
        size--;
        return removed;
    }

    // Remove from the rear. O(1)
    public FoodItem removeRear() {
        if (isEmpty()) {
            throw new StorageEmptyException(MAX_CAPACITY);
        }
        int rear = (head + size - 1) % MAX_CAPACITY;
        FoodItem removed = items[rear];
        items[rear] = null;
        size--;
        return removed;
    }

    // Look at the front item without removing it. O(1)
    public FoodItem peekFront() {
        if (isEmpty()) {
            throw new StorageEmptyException(MAX_CAPACITY);
        }
        return items[head];
    }

    // Look at the rear item without removing it. O(1)
    public FoodItem peekRear() {
        if (isEmpty()) {
            throw new StorageEmptyException(MAX_CAPACITY);
        }
        return items[(head + size - 1) % MAX_CAPACITY];
    }

    // All items from front to rear, for display. O(n)
    public FoodItem[] getAllFoodItems() {
        if (isEmpty()) {
            throw new StorageEmptyException(MAX_CAPACITY);
        }
        FoodItem[] current = new FoodItem[size];
        for (int i = 0; i < size; i++) {
            current[i] = items[(head + i) % MAX_CAPACITY];
        }
        return current;
    }

    // Linear search by name, returns null if not found. O(n)
    public FoodItem search(String name) {
        if (isEmpty()) {
            throw new StorageEmptyException(MAX_CAPACITY);
        }
        for (int i = 0; i < size; i++) {
            FoodItem item = items[(head + i) % MAX_CAPACITY];
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}