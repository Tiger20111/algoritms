package task2.graph;

import java.util.ArrayList;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

public class Graph {
    Node root;

    public Graph(Node root){
        this.root = root;
    }

    public void dfs(Node node, List<Integer> maxOnLevel, int curLevel){
        if (maxOnLevel.size() <= curLevel){
            maxOnLevel.add(node.key);
        } else {
            maxOnLevel.set(curLevel, max(maxOnLevel.get(curLevel), node.key));
        }
        if (node.left != null) {
            dfs(node.left, maxOnLevel, curLevel+1);
        }
        if (node.right != null) {
            dfs(node.right, maxOnLevel, curLevel+1);
        }
    }

    public List<Integer> findMax(){
        int curLevel = 0;
        List<Integer> maxOnLevel = new ArrayList<Integer>();
        dfs(this.root, maxOnLevel, curLevel);
        return maxOnLevel;
    }

}
