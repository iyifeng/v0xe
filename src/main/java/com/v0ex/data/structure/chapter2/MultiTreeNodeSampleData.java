package com.v0ex.data.structure.chapter2;

import com.alibaba.fastjson.JSON;

/**
 * @author bugcoder
 */
public class MultiTreeNodeSampleData {

    public static void main(String[] args) {
        MultiTreeNode<String> node = new MultiTreeNode<String>("root");
            MultiTreeNode<String> childNode1 = new MultiTreeNode<>("child1");
            node.addChild(childNode1);
                MultiTreeNode<String> childNode11 = new MultiTreeNode<>("child1-1");
                MultiTreeNode<String> childNode12 = new MultiTreeNode<>("child1-2");
                MultiTreeNode<String> childNode13 = new MultiTreeNode<>("child1-3");
            childNode1.addChild(childNode11);
            childNode1.addChild(childNode12);
            childNode1.addChild(childNode13);
        MultiTreeNode<String> childNode2 = new MultiTreeNode<>("child2");
            node.addChild(childNode2);
                MultiTreeNode<String> childNode21 = new MultiTreeNode<>("child2-1");
                MultiTreeNode<String> childNode22 = new MultiTreeNode<>("child2-2");
                MultiTreeNode<String> childNode23 = new MultiTreeNode<>("child3-3");
            childNode2.addChild(childNode21);
            childNode2.addChild(childNode22);
            childNode2.addChild(childNode23);
        MultiTreeNode<String> childNode3 = new MultiTreeNode<>("child3");
            node.addChild(childNode3);
        System.out.println(JSON.toJSONString(node));
    }
}
