package server.domain;

public class Node {
    private Car value;
    private Node getLeft;
    private Node right;
    private int height;


    public Node(Car value) {
        this.value = value;
        this.getLeft = null;
        this.right = null;
        this.height = 0;
    }

    public Node() {
        this.getLeft = null;
        this.right = null;
        this.height = 1;
    }

    public Car getValue() {
        return value;
    }

    public void setValue(Car value) {
        this.value = value;
    }

    public Node getLeft() {
        return getLeft;
    }

    public void setLeft(Node left) {
        this.getLeft = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
