package Exceptions;

public class KeyDoesNotExistsException extends RuntimeException {
    public KeyDoesNotExistsException() {
    }

    public KeyDoesNotExistsException(int key) {
        super("Key does not exists: " + key);
    }

}
