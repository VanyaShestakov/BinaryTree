package MyBinaryTree;

public class KeyDoesNotExistsException extends IndexOutOfBoundsException {
    public KeyDoesNotExistsException(int key) {
        super("Key that not exists: " + key);
    }
}
