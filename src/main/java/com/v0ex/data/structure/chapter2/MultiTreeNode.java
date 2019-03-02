package com.v0ex.data.structure.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bugcoder
 */
public class MultiTreeNode<T> {

    private T data;

    private List<MultiTreeNode> children;

    private MultiTreeNode parent;

    public MultiTreeNode(){
        children = new ArrayList<>();
    }

    public MultiTreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(MultiTreeNode<T> child){
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data){
        MultiTreeNode<T> multiTreeNode = new MultiTreeNode<>(data);
        this.addChild(multiTreeNode);
    }

    public void addChild(List<MultiTreeNode<T>> children){
        for (MultiTreeNode node : children){
            node.setParent(this);
        }
        this.children.addAll(children);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<MultiTreeNode> getChildren() {
        return children;
    }

    public MultiTreeNode getParent() {
        return parent;
    }

    public void setParent(MultiTreeNode parent) {
        this.parent = parent;
    }
}
