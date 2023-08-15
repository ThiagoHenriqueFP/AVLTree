package server.infraestructure;

import server.domain.Car;
import server.domain.Node;

import java.util.logging.Logger;

public class TreeAVL {
    private static int ROTATIONS = 0;
    private static int HEIGHT = 0;
    private static int COUNT = 0;
    private final Logger logger = Logger.getLogger("logger avl");
    private Node node;

    public TreeAVL(Node root) {
        this.node = root;
    }

    public TreeAVL() {
        this.node = new Node();
    }

    public static int getRotations() {
        return ROTATIONS;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        TreeAVL.HEIGHT = HEIGHT;
    }

    public static int getCOUNT() {
        return COUNT;
    }

    public static void setCOUNT(int COUNT) {
        TreeAVL.COUNT = COUNT;
    }

    public void insert(Car element) {
        setRoot(insert(element, getRoot()));
    }

    private Node insert(Car element, Node node) {
        if (node == null)
            node = new Node();

        if (node.getValue() == null) {
            node.setValue(element);
        } else if (element.getRenavan().compareTo(node.getValue().getRenavan()) < 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else if (element.getRenavan().compareTo(node.getValue().getRenavan()) > 0) {
            node.setRight(insert(element, node.getRight()));
        } else {
            throw new IllegalArgumentException("renavan must be unique");
        }
        Integer greater = greater(node.getLeft(), node.getRight());

        if (greater > HEIGHT) {
            setHEIGHT(greater + 1);
            logger.info("Height: " + getHEIGHT());
        }

        node.setHeight(1 + greater);

        Integer balance = balanceFactor(node);
        Integer balanceLeft = balanceFactor(node.getLeft());
        Integer balanceRight = balanceFactor(node.getRight());

        if (balance > 1 && balanceLeft >= 0) {
            return this.simpleRightRotation(node);
        } else if (balance > 1 && balanceLeft < 0) {
            node.setLeft(simpleLeftRotation(node.getLeft()));
            return this.simpleRightRotation(node);
        } else if (balance < -1 && balanceRight <= 0) {
            return this.simpleLeftRotation(node);
        } else if (balance < -1 && balanceRight > 0) {
            node.setRight(simpleRightRotation(node.getRight()));
            return this.simpleLeftRotation(node);
        }

        return node;
    }

    private Node simpleLeftRotation(Node node) {
        Node reservedTree = node.getRight();
        Node rotated = reservedTree.getLeft();

        reservedTree.setLeft(node);
        node.setRight(rotated);

        node.setHeight(1 + this.greater(node.getLeft(), node.getRight()));
        reservedTree.setHeight(1 + this.greater(reservedTree.getLeft(), reservedTree.getRight()));
        logger.info("Left rotation complete. Total=" + (++ROTATIONS));
        return reservedTree;
    }

    private Node simpleRightRotation(Node node) {
        Node reservedTree = node.getLeft();
        Node rotated = reservedTree.getRight();

        reservedTree.setRight(node);
        node.setLeft(rotated);

        node.setHeight(1 + this.greater(node.getLeft(), node.getRight()));
        reservedTree.setHeight(1 + this.greater(reservedTree.getLeft(), reservedTree.getRight()));

        logger.info("right rotation complete. Total=" + (++ROTATIONS));
        return reservedTree;
    }

    private Integer greater(Node left, Node right) {
        Integer leftHeight = -1;
        Integer rightHeight = -1;

        if (left != null) leftHeight = left.getHeight();
        if (right != null) rightHeight = right.getHeight();

        return Math.max(leftHeight, rightHeight);
    }

    private Integer balanceFactor(Node root) {
        if (root == null)
            return 0;

        Integer leftHeight = -1;
        Integer rightHeight = -1;

        if (root.getLeft() != null) leftHeight = root.getLeft().getHeight();
        if (root.getRight() != null) rightHeight = root.getRight().getHeight();

        return leftHeight - rightHeight;
    }

    public Car search(String key) {
        return search(key, getRoot());
    }

    private Car search(String key, Node node) {
        String extractedKey = node.getValue().getRenavan();
        if (extractedKey.equals(key))
            return node.getValue();
        else if (key.compareTo(extractedKey) < 0)
            return search(key, node.getLeft());
        else if (key.compareTo(extractedKey) > 0)
            return search(key, node.getRight());
        return null;
    }

    private Node searchAndGetNode(String key, Node node) {
        String extractedKey = node.getValue().getRenavan();
        if (extractedKey.equals(key))
            return node;
        else if (key.compareTo(extractedKey) < 0)
            return searchAndGetNode(key, node.getLeft());
        else if (key.compareTo(extractedKey) > 0)
            return searchAndGetNode(key, node.getRight());
        return null;
    }

    private Node minValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null)
            current = current.getLeft();

        return current;
    }

    public Car remove(String key) {
        return remove(key, getRoot()).getValue();
    }

    private Node remove(String key, Node node) {

        if (node == null)
            throw new NullPointerException("This value not exists in this tree");

        String extractedKey = node.getValue().getRenavan();
        if (key.compareTo(extractedKey) < 0)
            remove(key, node.getLeft());
        else if (key.compareTo(extractedKey) > 0)
            remove(key, node.getRight());
        else if (node.getLeft() != null && node.getRight() != null) {
            node.setValue(minValueNode(node.getRight()).getValue());
            node.setRight(remove(node.getValue().getRenavan(), node.getRight()));
        } else {
            node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        }

        if (node == null) return node;

        Integer leftHeight = node.getLeft().getHeight();
        Integer rightHeight = node.getRight().getHeight();

        node.setHeight(Math.max(leftHeight, rightHeight) + 1);

        Integer balance = balanceFactor(node);
        Integer balanceLeft = balanceFactor(node.getLeft());
        Integer balanceRight = balanceFactor(node.getRight());

        if (balance > 1 && balanceLeft >= 0) {
            return this.simpleRightRotation(node);
        } else if (balance > 1 && balanceLeft < 0) {
            node.setLeft(simpleLeftRotation(node.getLeft()));
            return this.simpleRightRotation(node);
        } else if (balance < -1 && balanceRight <= 0) {
            return this.simpleLeftRotation(node);
        } else if (balance < -1 && balanceRight > 0) {
            node.setRight(simpleRightRotation(node.getRight()));
            return this.simpleLeftRotation(node);
        }

        return node;

    }

    public Car put(Car car) {
        return put(car.getRenavan(), car);
    }

    private Car put(String key, Car car) {
        String extractedKey = node.getValue().getRenavan();
        if (extractedKey.equals(key)) {
            node.setValue(car);
            return node.getValue();
        } else if (key.compareTo(extractedKey) < 0)
            return search(key, node.getLeft());
        else if (key.compareTo(extractedKey) > 0)
            return search(key, node.getRight());
        throw new NullPointerException("This car not exists on this database");
    }

    public void getAll() {
        getAll(this.getRoot());
    }

    private void getAll(Node node) {
        if (node != null) {
            getAll(node.getLeft());
            System.out.println(node.getValue());
            getAll(node.getRight());
            setCOUNT(++COUNT);
        } else {
            return;
        }
    }

    public Node getRoot() {
        return node;
    }

    public void setRoot(Node root) {
        this.node = root;
    }

}
