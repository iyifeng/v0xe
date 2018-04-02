package com.v0ex.data.structure.chapter2;

/**
 * 求整数单链表的平均值
 *
 * Created by zbj on 17/4/2.
 */
public class SinglyLinkedList_average {

    public static Integer[] random(int n){
        Integer[] element = new Integer[n];
        for (int i = 0; i < n; i++) {
            element[i]= new Integer((int)Math.random()*100);//产生随机数
        }
        return element;
    }

    public static double averageExceptionMaxMin(SinglyLinkedList<Integer> list){
        if (list.isEmpty())
            throw new IllegalArgumentException("不能对空指针单链表求平均值");
        int sum = 0,i=0,max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
        Node<Integer> p = list.head.next;//要求head权限必须是public
        while (p!=null){
            int val  = p.data.intValue();
            sum += val;
            if (val>max)
                max = val;
            if (val<min)
                min = val;
            p = p.next;
            i++;
        }
        if (i==1||i==2){
            return (double)sum/i;//返回两个元素的平均值，避免了除数为0和1的错误
        }
        return (double)(sum-max-min)/(i-2);//返回去掉最大值和最小值后的平均值
    }
}
