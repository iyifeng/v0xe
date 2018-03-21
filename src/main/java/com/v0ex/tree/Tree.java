package com.v0ex.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zbj on 17/3/2.
 */
public class Tree<T> {

    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    private static class Node<T>{

        private T data;

        private Node<T> parent;

        private List<Node<T>> children;
    }
}
