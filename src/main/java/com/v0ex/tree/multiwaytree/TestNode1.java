package com.v0ex.tree.multiwaytree;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by zbj on 18/3/3.
 */
public class TestNode1 {

    public static void main(String[] args) {
        Node1 root = new Node1("C1");
        Node1 c2 = new Node1("C2", root);
        root.addNode(c2);
        //System.out.println(root.getChildNodes().size());
        root.dynamicAddNode(new Node1("C3",c2));
        root.dynamicAddNode(new Node1("C4",new Node1("C2")));
        c2.getChildNodes().stream().forEach(o -> {
            Node1 o1 = (Node1) o;
            System.out.println(o1.getData().toString());
        });
        //printNodes(root);
        System.out.println(JSON.toJSONString(root.getTreeNodeByData("C2")));
    }

    public static void printNodes(Node1 node1){
        System.out.println(node1.getData().toString());
        if (null == node1.getChildNodes()){
            return;
        }
        List<Node1> childNodes = node1.getChildNodes();
        childNodes.stream().forEach(o -> {
            if (null != o){
                printNodes(o);
            }
        });
    }
}
