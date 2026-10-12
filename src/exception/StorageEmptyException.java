package exception;

public class StorageEmptyException extends RuntimeException {
    public StorageEmptyException() {
        super("Storage is empty");
    }
}