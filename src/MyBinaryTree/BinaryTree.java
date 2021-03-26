package MyBinaryTree;

import java.util.*;

public class BinaryTree<T> {

    private static class Node<T> {
        int key;
        T value;
        Node<T> right;
        Node<T> left;

        public Node(int key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int MAX_KEY = 999;
    private Node<T> root;

    public void add(int key, T value) {
        if (key < MAX_KEY) {
            Node<T> node = new Node<>(key, value);
            if (root != null) {
                Node<T> current = root;
                Node<T> prev;
                boolean posNotFound = true;
                while (posNotFound) {
                    prev = current;
                    if (key < current.key) {
                        current = current.left;
                        if (current == null) {
                            prev.left = node;
                            posNotFound = false;
                        }
                    } else {
                        current = current.right;
                        if (current == null) {
                            prev.right = node;
                            posNotFound = false;
                        }
                    }
                }
            } else {
                root = node;
            }
        }
    }

    public void delete(int key) {
        root = deleteRecursively(root, key);
    }

    public T get(int key) {
        Node<T> current = root;
        if (root != null) {
            if (key != root.key) {
                while (current.key != key) {
                    if (key < current.key) {
                        current = current.left;
                    } else {
                        current = current.right;
                    }
                    if (current == null) {
                        return null;
                    }
                }
            } else {
                return root.value;
            }
        } else {
            return null;
        }
        return current.value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        return recursiveDepthFirst(root, result);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(int key) {
        return findNode(key) != null;
    }

    public void clear() {
        root = null;
    }

    public void print() {
        if (root != null) {
            int digits = getMaxAmountOfDigits();
            Stack<Node<T>> levelStack = new Stack<>();
            levelStack.push(root);
            int height = getHeight(root);
            int interval = (int) Math.pow(2, height);
            boolean isRowEmpty = false;
            while (!isRowEmpty) {
                Stack<Node<T>> childStack = new Stack<>();
                isRowEmpty = true;
                for (int i = 0; i < interval; i++) {
                    System.out.print(" ");
                }
                while (!levelStack.isEmpty()) {
                    Node<T> temp = levelStack.pop();
                    if (temp != null) {
                        System.out.print(temp.key);
                        int emptyDigits = digits - Integer.toString(temp.key).length();
                        for (int i = 0; i < emptyDigits; i++) {
                            System.out.print(" ");
                        }
                        childStack.push(temp.left);
                        childStack.push(temp.right);
                        if (temp.left != null || temp.right != null) {
                            isRowEmpty = false;
                        }
                    } else {
                        System.out.print("__");
                        childStack.push(null);
                        childStack.push(null);
                    }
                    for (int i = 0; i < interval * 2 - 2; i++) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
                interval /= 2;
                while (!childStack.isEmpty()) {
                    levelStack.push(childStack.pop());
                }
            }
            System.out.println("--------------------");
            viewSomeNodes();
        } else {
            System.out.println("Tree is empty");
        }
    }

    public String toStringBreadthFirst() {
        StringBuilder result = new StringBuilder();
        if (root != null) {
            Queue<Node<T>> nodes = new LinkedList<>();
            nodes.add(root);
            while (!nodes.isEmpty()) {
                Node<T> current = nodes.remove();
                result.append("Key:").append(current.key).append("; Value:").append(current.value).append("\n");
                if (current.left != null) {
                    nodes.add(current.left);
                }

                if (current.right != null) {
                    nodes.add(current.right);
                }
            }
        } else {
            result.append("");
        }
        return result.toString();
    }

    private String recursiveDepthFirst(Node<T> current, StringBuilder result) {
        if (current != null) {
            recursiveDepthFirst(current.left, result);
            result.append("Key:").append(current.key).append("; Value:").append(current.value).append("\n");
            recursiveDepthFirst(current.right, result);
        }
        return result.toString();
    }

    private Node<T> findNode(int key) {
        if (root == null) {
            return null;
        }
        Node<T> current = root;
        if (key == root.key) {
            return root;
        } else {
            while (current.key != key) {
                if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
                if (current == null) {
                    return null;
                }
            }
        }
        return current;
    }

    private Node<T> deleteRecursively(Node<T> current, int key) {
        if (current == null) {
            return null;
        }
        if (key < current.key) {
            current.left = deleteRecursively(current.left, key);
            return current;
        } else if (key > current.key) {
            current.right = deleteRecursively(current.right, key);
            return current;
        }
        if (current.left == null) {
            return current.right;
        } else if (current.right == null) {
            return current.left;
        } else {
            current.key = findSmallestKey(current.right);
            current.right = deleteRecursively(current.right, current.key);
            return current;
        }
    }

    private int findSmallestKey(Node<T> current) {
        return current.left == null ? current.key : findSmallestKey(current.left);
    }

    private int getHeight(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private void viewSomeNodes() {
        if (root != null) {
            Queue<Node<T>> nodes = new LinkedList<>();
            nodes.add(root);
            while (!nodes.isEmpty()) {
                Node<T> current = nodes.remove();
                if (Math.abs(findAmountOfNodes(current.left) - findAmountOfNodes(current.right)) == 1) {
                    System.out.println("Key:" + current.key + "; Value:" + current.value);
                }
                if (current.left != null) {
                    nodes.add(current.left);
                }
                if (current.right != null) {
                    nodes.add(current.right);
                }
            }
        }
    }

    private int findAmountOfNodes(Node<T> current) {
        LinkedList<Node<T>> nodes = new LinkedList<>();
        return findAmountRecursively(current, nodes);
    }

    private int findAmountRecursively(Node<T> current, LinkedList<Node<T>> nodes) {
        if (current != null) {
            findAmountRecursively(current.left, nodes);
            nodes.add(current);
            findAmountRecursively(current.right, nodes);
        }
        return nodes.size();
    }

    private int getMaxAmountOfDigits() {
        ArrayList<Integer> keys = new ArrayList<>();
        getKeys(root, keys);
        String maxKey = Collections.max(keys).toString();
        return maxKey.length();
    }

    private void getKeys(Node<T> current, ArrayList<Integer> keys) {
        if (current != null) {
            getKeys(current.left, keys);
            keys.add(current.key);
            getKeys(current.right, keys);
        }
    }

}
