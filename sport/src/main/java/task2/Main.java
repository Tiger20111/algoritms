package task2;

import task2.graph.Graph;
import task2.graph.Node;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Node v1 = new Node(0, null, null);
        Node v2 = new Node(2, null, null);
        Node root = new Node(1, v1, v2);
        Graph g = new Graph(root);

        List<Integer> res = g.findMax();
        System.out.println(res);
    }
}
