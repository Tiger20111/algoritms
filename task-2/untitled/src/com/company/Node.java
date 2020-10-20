package com.company;

public class Node {
    Node left;
    Node right;
    int key;

    Node(int key, Node left, Node right){
        this.key = key;
        this.left = left;
        this.right = right;
    }
}
