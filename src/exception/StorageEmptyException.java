package exception;

// Thrown when removing, peeking, displaying or searching an empty storage
public class StorageEmptyException extends RuntimeException {
    public StorageEmptyException(int capacity) {
        super("Storage is empty with capacity: " + capacity);
    }
}