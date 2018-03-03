package com.v0ex.tree.multiwaytree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zbj on 18/3/3.
 */
public class Node1<T> implements Serializable{

    private T data;

    private Node1<T> parentNode;

    private List<Node1> childNodes;

    public Node1(T data) {
        this.data = data;
    }

    public Node1(T data, Node1<T> parentNode) {
        this.data = data;
        this.parentNode = parentNode;
    }

    public Node1() {
    }

    public void addNode(Node1 node){
        node.setParentNode(this);
        if (null == this.childNodes){
            this.childNodes = new ArrayList<Node1>();
        }
        this.childNodes.add(node);
    }

    public boolean dynamicAddNode(Node1 node){
        Node1 parentNode = node.getParentNode();
        if (this.data == parentNode.data){
            addNode(node);
            return true;
        }else {
            List<Node1> childNodes = this.childNodes;
            int childNum = childNodes.size();
            boolean addFlag;
            for (int i = 0; i < childNum; i++) {
                Node1 childNode = childNodes.get(i);
                addFlag = childNode.dynamicAddNode(node);
                if (addFlag)return true;
            }
            return false;
        }
    }

    public List<Node1> getJuniorNodes(){
        List<Node1> tempNodes = new ArrayList<Node1>();
        List<Node1> childNodes = this.childNodes;
        if (null == childNodes){
            return tempNodes;
        }else {
            int childNum = childNodes.size();
            for (int i = 0; i < childNum; i++) {
                Node1 childNode = childNodes.get(i);
                tempNodes.add(childNode);
                tempNodes.addAll(childNode.getJuniorNodes());
            }
            return tempNodes;
        }
    }

    public Node1 getTreeNodeByData(T data){
        if (this.data == data)return this;
        if (null == this.childNodes){
            return null;
        }else {
            int childNodeNum = childNodes.size();
            for (int i = 0; i < childNodeNum; i++) {
                Node1 treeNode = childNodes.get(i);
                Node1 resultNode = treeNode.getTreeNodeByData(data);
                if (null != resultNode)return resultNode;
            }
            return null;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node1<T> getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node1<T> parentNode) {
        this.parentNode = parentNode;
    }

    public List<Node1> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node1> childNodes) {
        this.childNodes = childNodes;
    }
}
