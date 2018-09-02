HashMap的put方法大致流程

1. 首次添加，初始化Map数组，根据key的hashcode找出key的位置，添加value。
2. 根据key的hashcode找出key的位置，如果Map数组中该位置为空，直接添加。
3. 如果该位置不为空，针对链表和红黑树结构
   1. key与该位置链表的首节点的key相同，覆盖旧的value
   2. 该位置是红黑树，在红黑树上添加value或者覆盖旧的value
   3. 遍历链表，添加添加value或者覆盖旧的value，遍历过程中如果链表的节点数达到8
      1. 如果Map中的键值对数量小于64，对Map进行扩容
      2. 大于64，该链表调整为红黑树

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //如果HashMap的节点数组为空,执行resize(),初始化节点数组
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        //(n-1)&hash与位操作，可以获得数组的第i个位置,如果该位置的节点为空，把key和value组成的节点添加到该位置，注意同时p也指向了该位置的节点    
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
        	//Node表示是一个链表结构
            Node<K,V> e; K k;
            //当第i个位置的节点不为空，也就是p节点不为空时，此时如果要添加的key的hashcode与p节点的hashcode相同，同时key与p节点的key相同，则把e节点指向p。
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            //当第i个位置也就是p节点是个红黑树时，用key和value构造一个红黑树的节点，然后把e节点指向该红黑树的节点    
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
            //当第i个位置的节点不为空，也就是p节点不为空时，此时如果要添加的key的hashcode与p节点的hashcode相同，但是key与p节点的key不相同，那就遍历以p节点为首的链表
                for (int binCount = 0; ; ++binCount) {
                	//找到p节点为首的链表的尾节点，在其尾部插入以key和value组成的节点，然后结束遍历
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        //如果添加了节点以后，该链表的节点个数已达到8个，则进行treeifyBin
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        	//该操作会先判断HashMap的数组长度是否小于64，小于则进行扩容，不小于则把当前链表结构调整为红黑树
                            treeifyBin(tab, hash);
                        break;
                    }
                    //遍历的过程中，如果链表上的某个节点的key的hashcode与要添加的key的hashcode相同，同时这个节点的key与要添加的key相同，则结束遍历
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    //遍历过程中，p节点跟随者遍历后移    
                    p = e;
                }
            }
            //e不为空，说明已经存在key的键值对，则用要添加的value覆盖并返回旧的value值
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        //Hashmap中节点的总数大于阈值（容量 * 加载因子，初始值是16*0.75=12），则进行扩容操作，阈值
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```

