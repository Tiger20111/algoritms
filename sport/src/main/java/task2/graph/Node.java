package task2.graph;

public class Node {
    Node left;
    Node right;
    int key;

    public Node(int key, Node left, Node right){
        this.key = key;
        this.left = left;
        this.right = right;
    }
}
