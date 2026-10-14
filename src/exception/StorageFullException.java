package exception;

// Thrown when adding to a storage that already holds MAX_CAPACITY items
public class StorageFullException extends RuntimeException {
    public StorageFullException(int capacity) {
        super("Storage is full with capacity: " + capacity);
    }
}