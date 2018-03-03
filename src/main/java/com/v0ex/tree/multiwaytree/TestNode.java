package com.v0ex.tree.multiwaytree;

import java.util.List;

/**
 * Created by zbj on 18/3/3.
 */
public class TestNode {

    public static void main(String[] args) {
        Node root = new Node("C1");
        Node C2 = new Node("C2");
        Node C3 = new Node("C3");
        Node C4 = new Node("C4");
        root.addChildNode(C2);
        C2.addChildNode(C3);
        C2.addChildNode(C4);
        //System.out.println(root.getNodeEntity());
        //printNodes(C3);
        List<Node> childNodes = root.getChildNodes();
        childNodes.stream().forEach(o -> {
            System.out.println(o.getNodeEntity().toString());
        });
    }

    public static void printNodes(Node<String> node){
        System.out.println(node.getNodeEntity());
        if (node.getChildNodes()==null)return;
        for (Node<String> childNode : node.getChildNodes()){
            System.out.println(childNode.getNodeEntity());
            if (null != childNode.getChildNodes()){
                printNodes(childNode);
            }
        }
    }
}
