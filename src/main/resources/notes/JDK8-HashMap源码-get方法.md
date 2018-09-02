HashMap的get方法大致流程

1. 根据key的hashcode找出key的位置，如果Map数组中该位置上的节点为空，返回null
2. 如果Map数组中该位置上的节点非空
   1. 该位置节点，也就是首节点正是要查找的节点，则返回该首节点
   2. 如果要查找的key与首节点的key不相同，
      1. 如果位置节点是红黑树，则在红黑树中查询key，找到返回对应的节点，找不到返回null
      2. 如果该位置节点是链表，则在链表中遍历查询，找到返回对应的节点，找不到返回null

```
final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        //如果HashMap的节点数组非空，根据key的hash值&(n-1)位与运算，找到数组中的位置，这个位置上的节点可以看做是首节点(想想数组+链表结构中，数组中的节点就是这个链表的首节点)，同时判断该首节点是否为空，如果为空，则说明key值在HashMap中不存在，返回null
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            //首节点非空，要查询的key的hashcode值与首节点的hash值相等，同时key值与首节点的key值相等，说明该首节点就是想要的节点，则返回该首节点
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            //如果要查询的key的hashcode值与首节点的hash值相等，但是key值不相等则遍历该数组位置的链表或者红黑树
            //注意这里有个隐含的条件，就是在HashMap执行put操作时，key的hashcode相同的键值对，会放在HashMap数组的同一个位置，这些key的hashcode相同的键值对会在这个位置组成一个链表结构或者红黑树结构  
            if ((e = first.next) != null) {
            	//如果该位置的数据结构是一个红黑树，则从红黑树中查询想要的节点，有则返回，无则返回null
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                //如果该位置的数据结构是一个链表，遍历节点，找到key值相同的节点返回，找不到返回null  
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```

