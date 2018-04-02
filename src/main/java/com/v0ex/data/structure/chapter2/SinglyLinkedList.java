package com.v0ex.data.structure.chapter2;

/**
 * Created by zbj on 17/4/2.
 */
public class SinglyLinkedList<T> implements LList<T> {

    protected Node<T> head;

    public SinglyLinkedList() {
        this.head = new Node<>();
    }

    //由指定数组中的多个对象构造单链表，采用尾插法构造单链表


    public SinglyLinkedList(T[] element) {
        this();//创建空的单链表
        Node<T> rear = this.head;//rear指向单链表的最后一个结点
        for (int i = 0; i < element.length; i++) {
            rear.next = new Node<T>(element[i],null);//尾插入，创建结点链入rear结点之后
            rear = rear.next;//rear指向新的链尾结点
        }
    }

    @Override
    public boolean isEmpty() {
        return this.head.next==null;
    }

    @Override
    public int length() {
        int i = 0;
        Node<T> p = this.head.next;//p从单链表的第一个结点开始
        while (p!=null){
            i++;
            p = p.next;
        }
        return i;
    }

    @Override
    public T get(int i) {
        if (i>=0){
            Node<T> p = this.head.next;
            for (int j = 0; p!=null&&j < i; j++) {
                p = p.next;
            }
            if (p!=null)
                return p.data;//p指向第i个结点
        }
        return null;
    }

    @Override
    public void set(int i, T x) {
        if (null == x)return;
        if (i>=0){
            Node<T> p = this.head.next;
            for (int j = 0; p!=null&&j < i ; j++) {
                p = p.next;
            }
            if (p!=null){
                p.data = x;//p指向第i个结点
            }
        }else throw new IndexOutOfBoundsException(i+"");//抛出数组越界异常
    }

    @Override
    public void insert(int i, T x) {
        if (null == x)return;
        Node<T> p = this.head.next;
        for (int j = 0; p.next!=null&&j < i; j++) {
            p = p.next;
        }
        //插入x作为p结点的后继结点，包括头插入，中间、尾插入
        p.next = new Node<T>(x,p.next);
    }

    @Override
    public void append(T x) {
        insert(Integer.MAX_VALUE,x);
    }

    @Override
    public T remove(int i) {
        if (i>=0){
            Node<T> p = this.head.next;
            for (int j = 0; p.next!=null&&j <i ; j++) {
                p = p.next;
            }
            if (p.next!=null){
                T old = p.next.data;//获得原对象
                p.next = p.next.next;
                return old;
            }
        }
        return null;
    }

    @Override
    public void removeAll() {
        this.head.next = null;//垃圾自动回收
    }

    @Override
    public T search(T key) {
        if (key==null)return null;
        Node<T> p = this.head.next;
        while (p!=null){
            if (p.data.equals(key)){
                return p.data;
            }
        }
        return null;
    }
}
