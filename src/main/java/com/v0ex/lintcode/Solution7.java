package com.v0ex.lintcode;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * 中等
 * 二叉树的序列化和反序列化
 * 设计一个算法，并编写代码来序列化和反序列化二叉树。将树写入一个文件被称为“序列化”，读取文件后重建同样的二叉树被称为“反序列化”。
 * 如何反序列化或序列化二叉树是没有限制的，你只需要确保可以将二叉树序列化为一个字符串，并且可以将字符串反序列化为原来的树结构。
 * 给出一个测试数据样例， 二叉树{3,9,20,#,#,15,7}，表示如下的树结构：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class Solution7 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Solution7.class);

    public static String serialize(TreeNode root){
        if (root == null)return "{}";
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (null==node)continue;
            queue.add(node.left);
            queue.add(node.right);
        }
        while (queue.get(queue.size()-1) == null){
            queue.remove(queue.size()-1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(queue.get(0).val);
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i)==null){
                sb.append(",#");
            }else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static TreeNode deserialize(String data){
        if (data.equals("{}"))return null;
        String[] vals = data.substring(1,data.length()-1).split(",");
        ArrayList<TreeNode> queue = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.add(root);
        int index = 0;
        boolean isLeftChild = true;
        for (int i = 1; i < vals.length; i++) {
            if (!vals[i].equals("#")){
                TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                LOGGER.info("======["+index+"]======="+ JSON.toJSONString(node));
                if (isLeftChild){
                    queue.get(index).left = node;
                }else {
                    queue.get(index).right = node;
                }
                queue.add(node);
            }
            if (!isLeftChild){
                index++;
            }
            isLeftChild = !isLeftChild;
        }
        return root;
    }

    public static void main(String[] args) {
        String data = "{3,9,20,#,#,15,7}";
        deserialize(data);
    }

    static class TreeNode{

        public int val;

        public TreeNode left,right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
