package MyBinaryTree;

import Exceptions.EmptyTreeException;
import Exceptions.KeyDoesNotExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test
    void equalsTest() {
        BinaryTree<Integer> firstTree = new BinaryTree<>();
        BinaryTree<Integer> secondTree = new BinaryTree<>();
        BinaryTree<Integer> otherTree = new BinaryTree<>();
        ArrayList<Integer> objOfOtherClass = new ArrayList<>();

        firstTree.add(3, 1);
        firstTree.add(2, 2);
        firstTree.add(4, 3);

        secondTree.add(3, 1);
        secondTree.add(2, 2);
        secondTree.add(4, 3);

        otherTree.add(3, 0);
        otherTree.add(2, 2);
        otherTree.add(4, 3);

        Assertions.assertTrue(firstTree.equals(secondTree));
        Assertions.assertTrue(firstTree.equals(firstTree));
        Assertions.assertFalse(firstTree.equals(null));
        Assertions.assertFalse(firstTree.equals(objOfOtherClass));
        Assertions.assertFalse(firstTree.equals(otherTree));
    }

    @Test
    void hashCodeTest() {
        BinaryTree<Integer> firstTree = new BinaryTree<>();
        BinaryTree<Integer> secondTree = new BinaryTree<>();
        BinaryTree<Integer> otherTree = new BinaryTree<>();
        ArrayList<Integer> objOfOtherClass = new ArrayList<>();

        firstTree.add(3, 1);
        firstTree.add(2, 2);
        firstTree.add(4, 3);

        secondTree.add(3, 1);
        secondTree.add(2, 2);
        secondTree.add(4, 3);

        otherTree.add(3, 0);
        otherTree.add(2, 2);
        otherTree.add(4, 3);

        Assertions.assertEquals(firstTree.hashCode(), firstTree.hashCode());
        Assertions.assertEquals(firstTree.hashCode(), secondTree.hashCode());
        Assertions.assertNotEquals(firstTree.hashCode(), otherTree.hashCode());
        Assertions.assertNotEquals(firstTree.hashCode(), objOfOtherClass.hashCode());
    }
}