package com.v0ex.data.structure.chapter2;

/**
 * Created by zbj on 17/4/2.
 */
public class SinglyLinkedList_reverse {

    //单链表反转，泛型方法，返回值类型前面声明类型参数T
    public static <T> void reverse(SinglyLinkedList<T> list){
        Node<T> p = list.head.next,succ = null,front = null;
        while (p!=null){
            succ = p.next;//设置succ是p结点的后继结点
            p.next = front;//使p.next指向p结点的前驱结点
            front = p;
            p = succ;//p向后走一步
        }
        list.head.next = front;//头结点指向逆转后的第一个结点
    }
}
