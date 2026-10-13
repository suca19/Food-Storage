package exception;

public class StorageFullException extends RuntimeException {
    public StorageFullException(int capacity) {
        super("Storage is full with capacity: " + capacity);
    }
}