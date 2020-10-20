package task2.graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void test1(){
        Node v1 = new Node(0, null, null);
        Node v2 = new Node(2, null, null);
        Node root = new Node(1, v1, v2);
        Graph g = new Graph(root);

        List<Integer> res = g.findMax();
        assertEquals(res, Arrays.asList(1, 2));
    }

    @Test
    public void test2(){
        Node v5 = new Node(3, null, null);
        Node v4 = new Node(5, v5, null);
        Node v3 = new Node(2, null, null);
        Node v2 = new Node(4, v3, v4);
        Node v1 = new Node(0, null, null);
        Node root = new Node(1, v1, v2);
        Graph g = new Graph(root);

        List<Integer> res = g.findMax();
        assertEquals(res, Arrays.asList(1, 4, 5, 3));
    }
}