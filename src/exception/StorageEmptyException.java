package exception;

public class StorageEmptyException extends RuntimeException {
    public StorageEmptyException(int capacity) {
        super("Storage is empty with capacity: " + capacity);
    }
}