package com.company;

import MyBinaryTree.BinaryTree;
import java.io.*;
import java.util.*;

public class Main {
    static BinaryTree<String> tree = new BinaryTree<>();
    static Scanner sysScan = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenu();
        sysScan.close();
    }

    static void displayMenu() {
        final int VIEW = 1;
        final int ADD = 2;
        final int DELETE = 3;
        final int FIND = 4;
        final int DEPTH_FIRST_SEARCH = 5;
        final int BREADTH_FIRST_SEARCH = 6;
        final int READ = 7;
        final int SAVE = 8;
        final int ABOUT = 9;
        final int CLOSE = 0;
        System.out.println("""        
           ----------------------------   
                    LABA 5.2          
           ----------------------------
                    MAIN MENU
           ----------------------------
           1. VIEW TREE
           2. ADD ITEM
           3. DELETE ITEM
           4. FIND ITEM 
           5. DEPTH FIRST SEARCH
           6. BREADTH FIRST SEARCH
           7. READ ITEMS FROM FILE
           8. SAVE TREE TO THE FILE
           9. SHOW TASK
           0. CLOSE PROGRAM     
           """);
        int choice = inputChoice();
        switch (choice) {
            case VIEW:
                showTree();
                break;
            case ADD:
                addItem();
                break;
            case DELETE:
                deleteItem();
                break;
            case FIND:
                findItem();
                break;
            case DEPTH_FIRST_SEARCH:
                showDepthFirst();
                break;
            case BREADTH_FIRST_SEARCH:
                showBreadthFirst();
                break;
            case READ:
                readItemsFromFile();
                break;
            case SAVE:
                saveItemsToFile();
                break;
            case ABOUT:
                showDescription();
                break;
            case CLOSE:
                break;
        }
    }

    static void showTree() {
        System.out.println("""
        -----------
        BINARY TREE
        -----------""");
        tree.print();
        System.out.println("\nPress ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void addItem() {
        System.out.println("""
        --------
        ADD ITEM
        --------""");
        System.out.println("Enter the key of item:");
        int key = inputKey();
        System.out.println("Enter the value of item:");
        String value = inputValue();
        tree.add(key, value);
        System.out.println("\n*Item added successfully!*");
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void deleteItem() {
        System.out.println("""
        -----------
        DELETE ITEM
        -----------""");
        System.out.println("Enter the key of the item you want to remove:");
        int removableKey = inputKey();
        if (tree.contains(removableKey)) {
            tree.delete(removableKey);
            System.out.println("\n*Queue deleted successfully!*");
        } else {
            System.out.println("\nItem is missing in the tree!");
        }
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void findItem() {
        System.out.println("""
        ---------
        FIND ITEM
        ---------""");
        System.out.println("Enter the key of the item you want to find:");
        int searchKey = inputKey();
        if (tree.contains(searchKey)) {
            System.out.println("Result of search:\n" +
                    "Key:" + searchKey + "; Value:" + tree.get(searchKey));
        } else {
            System.out.println("Item is missing in the tree!");
        }
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void showDepthFirst() {
        System.out.println("""
        ------------------
        DEPTH FIRST SEARCH
        ------------------""");
        if (!tree.isEmpty()) {
            System.out.println(tree.toString());
        } else {
            System.out.println("Tree is empty!");
        }
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void showBreadthFirst() {
        System.out.println("""
        --------------------
        BREADTH FIRST SEARCH
        --------------------""");
        if (!tree.isEmpty()) {
            System.out.println(tree.toStringBreadthFirst());
        } else {
            System.out.println("Tree is empty!");
        }
        System.out.println("Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void readItemsFromFile() {
        System.out.println("""
        --------------------
        READ ITEMS FROM FILE
        --------------------""");
        System.out.println("Enter path to the file:");
        String path = inputPath();
        String line;
        boolean isCorrect = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (isCorrect && (line = reader.readLine()) != null) {
                if (line.matches("^\\d+ [A-Za-zА-Яа-яёЁ]+$")) {
                    addToTree(line);
                } else {
                    tree.clear();
                    System.out.println("Incorrect data in the file");
                    isCorrect = false;
                }
            }
        } catch (IOException e) {
            System.out.println("\nInput error!");
        }
        String succMessage = isCorrect ? "Data read successfully!\n" : "";
        System.out.println(succMessage + "Press ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void saveItemsToFile() {
        System.out.println("""
        ------------------
        SAVE ITEMS TO FILE
        ------------------""");
        System.out.println("Enter path to the file:");
        String path = inputPath();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.append(tree.toString());
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Output error!");
        }
        System.out.println("\nPress ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void showDescription() {
        System.out.println("""
        ----
        TASK
        ----""");
        System.out.println("The program implements work with a binary tree.\n" +
                "The program displays the numbers of vertices whose number of\n" +
                "children in the right subtree differs from the number of\n" +
                "children in the left subtree by 1");
        System.out.println("\nPress ENTER");
        sysScan.nextLine();
        displayMenu();
    }

    static void addToTree(String line) {
        String[] items = line.split(" ");
        int key = Integer.parseInt(items[0]);
        String value = items[1];
        tree.add(key, value);
    }

    static String inputPath() {
        String path;
        boolean isNotExists;
        do {
            isNotExists = false;
            path = sysScan.nextLine();
            File inputFile = new File(path);
            if (!inputFile.exists()) {
                System.err.println("File not found. Repeat enter!");
                isNotExists = true;
            }
        }while (isNotExists);
        return path;
    }

    static int inputChoice() {
        int choice = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            try {
                choice = Integer.parseInt(sysScan.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Enter the integer number!");
                isIncorrect = true;
            }
            if (!isIncorrect && (choice < 0 || choice > 9)) {
                System.err.println("Enter the number in range [0; 9]");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return choice;
    }

    static int inputKey() {
        int key = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            try {
                key = Integer.parseInt(sysScan.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Enter the integer number!");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return key;
    }

    static String inputValue() {
        String value;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            value = sysScan.nextLine();
            if (value.isEmpty() || value.matches("^\\s+$")) {
                System.err.println("Line is empty. Repeat enter!");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return value;
    }
}
