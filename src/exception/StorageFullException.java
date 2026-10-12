package exception;

public class StorageFullException extends RuntimeException {
    public StorageFullException() {
        super("Storage is full");
    }
}