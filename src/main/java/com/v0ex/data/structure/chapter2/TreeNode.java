package com.v0ex.data.structure.chapter2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zbj
 */
public class TreeNode<T> implements Iterable<TreeNode<T>>{

    public T data;

    public TreeNode<T> parent;

    public List<TreeNode<T>> children;


    public TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<>();
    }

    public boolean isRoot(){
        return null == parent;
    }

    public boolean isChildren(){
        return children.size() == 0;
    }

    public TreeNode<T> addChild(T child){
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }


    public int getLevel(){
        if (this.isRoot()){
            return 0;
        }else {
            return parent.getLevel() + 1;
        }
    }


    @Override
    public String toString() {
        return data != null ? data.toString() : "[data null]";
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        TreeNodeIterator iterator = new TreeNodeIterator(this);
        return iterator;
    }
}
