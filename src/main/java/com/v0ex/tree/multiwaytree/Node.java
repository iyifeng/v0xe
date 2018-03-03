package com.v0ex.tree.multiwaytree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zbj on 18/3/3.
 */
public class Node<T> implements Serializable{

    private static final long serialVersionUID = -8762908780598960883L;

    private Node parentNode;

    private T nodeEntity;

    private List<Node> childNodes;

    public Node(T nodeEntity) {
        this.nodeEntity = nodeEntity;
    }

    public Node(){

    }

    public void addChildNode(Node childNode){
        childNode.setParentNode(this);
        if (null == this.childNodes){
            this.childNodes = new ArrayList<Node>();
        }
        this.childNodes.add(childNode);
    }

    public void removeChildNode(Node childNode){
        if (null != this.childNodes){
            this.childNodes.remove(childNode);
        }
    }

    public T getNodeEntity() {
        return nodeEntity;
    }

    public void setNodeEntity(T nodeEntity) {
        this.nodeEntity = nodeEntity;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
}
