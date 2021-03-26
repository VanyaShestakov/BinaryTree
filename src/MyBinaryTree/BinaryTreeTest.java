package MyBinaryTree;

import Exceptions.EmptyTreeException;
import Exceptions.KeyDoesNotExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Optional;

public class BinaryTreeTest {

    @Test
    void toStringBreadthFirst() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int[] expectedKeys = {50, 25, 75, 11, 85};
        for (int key: expectedKeys) {
            tree.add(key, 1);
        }
        StringBuilder expectedStr = new StringBuilder();
        for (int key: expectedKeys) {
            expectedStr.append("Key:").append(key).append("; Value:").append(1).append("\n");
        }
        String expected = expectedStr.toString();
        String actual = tree.toStringBreadthFirst();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int[] sourceKeys = {10, 4, 7, 3, 8, 6};
        for (int key: sourceKeys) {
            tree.add(key, 1);
        }
        tree.add(1, 1);
        int[] expectedKeys = {10, 4, 3, 7, 1, 6, 8};
        StringBuilder expectedStr = new StringBuilder();
        for (int key: expectedKeys) {
            expectedStr.append("Key:").append(key).append("; Value:").append(1).append("\n");
        }
        String expected = expectedStr.toString();
        String actual = tree.toStringBreadthFirst();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Assertions.assertThrows(EmptyTreeException.class,() -> tree.get(100));
        int key = 100;
        int expectedValue = 123;
        tree.add(key, expectedValue);
        Assertions.assertEquals(Optional.of(expectedValue), Optional.ofNullable(tree.get(key)));
        Assertions.assertThrows(KeyDoesNotExistsException.class ,() -> tree.get(1));
    }

    @Test
    void deleteTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int[] sourceKeys = {10, 4, 3, 7, 6, 8};
        for (int key: sourceKeys) {
            tree.add(key, 1);
        }
        tree.delete(7);
        int[] expectedKeys = {10, 4, 3, 8, 6};
        StringBuilder expectedStr = new StringBuilder();
        for (int key: expectedKeys) {
            expectedStr.append("Key:").append(key).append("; Value:").append(1).append("\n");
        }
        String expected = expectedStr.toString();
        String actual = tree.toStringBreadthFirst();
        Assertions.assertEquals(expected, actual);
        Assertions.assertFalse(tree.contains(7));
    }

    @Test
    void toStringTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int[] keys = {10, 4, 3, 7, 6, 8};
        for (int key: keys) {
            tree.add(key, 1);
        }
        Arrays.sort(keys);
        StringBuilder expectedStr = new StringBuilder();
        for (int key: keys) {
            expectedStr.append("Key:").append(key).append("; Value:").append(1).append("\n");
        }
        String expected = expectedStr.toString();
        String actual = tree.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isEmptyTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Assertions.assertTrue(tree.isEmpty());
        tree.add(4, 1);
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    void clearTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5, 4);
        tree.add(2, 456);
        tree.add(8, 543);
        Assertions.assertFalse(tree.isEmpty());
        tree.clear();
        Assertions.assertThrows(EmptyTreeException.class,() -> tree.get(5));
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void containsTest() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int[] keys = {10, 4, 3, 7, 6, 8};
        for (int key: keys) {
            tree.add(key, 1);
        }
        Assertions.assertTrue(tree.contains(10));
        Assertions.assertFalse(tree.contains(50));
        tree.clear();
        Assertions.assertFalse(tree.contains(10));
    }

}