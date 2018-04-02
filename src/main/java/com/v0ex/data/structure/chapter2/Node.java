package com.v0ex.data.structure.chapter2;

/**
 * Created by zbj on 17/4/2.
 */
public class Node<T> {

    public T data;//数据域

    public Node<T> next;//地址域，引用后继结点

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node() {
        this(null,null);
    }
}
