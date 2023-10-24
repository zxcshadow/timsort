package linked;

public class Node {
    int data;
    Node left;
    Node right;
    int height;

    public Node(int data) {
        this.data = data;
        height = 1;
        right = null;
        left = null;
    }
}
