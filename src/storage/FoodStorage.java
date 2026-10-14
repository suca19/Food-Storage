package storage;

import model.FoodItem;

public class FoodStorage {

    public static final int MAX_CAPACITY = 8;
    private final FoodItem[] items = new FoodItem[MAX_CAPACITY];
    private int size = 0;
    private int head = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == MAX_CAPACITY;
    }

    public int size() {
        return size;
    }

}   