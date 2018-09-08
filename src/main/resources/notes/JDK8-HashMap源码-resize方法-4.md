HashMap的resize方法大致流程 

1. 首次使用，初始化数组容量和阈值，容量默认16，阈值默认12
2. 扩容2倍，容量扩容2倍，阈值也扩容
   1. 如果原位置节点是单节点，直接放到新数组的hashcode&(newCap - 1)的位置
   2. 如果原位置节点是红黑树，则调整红黑树
   3. 如果是链表，用两个链表来重hash，消0放到新数组的原位置，不消0放到新数组的（原位置+旧数组长度）位置

```
final Node<K,V>[] resize() {
		//临时保存旧的数组
        Node<K,V>[] oldTab = table;
        //旧的数组长度
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        //旧的阈值
        int oldThr = threshold;
        //声明新的数组长度和阈值
        int newCap, newThr = 0;
        //判断扩容前的旧数组长度
        if (oldCap > 0) {
        	//如果旧数组长度大于等于1<<30
            if (oldCap >= MAXIMUM_CAPACITY) {
            	//则设置HashMap的全局阈值为整型最大值,然后直接返回旧的数组
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            //否则，也就是HashMap扩容前的容量还没有到达最大1<<30
            //那么新的数组容量扩容一倍，还能同时满足就数组长度大于默认长度16(这里主要为了区别HashMap的初始化)，那么新的阈值也扩容一倍
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        //如果扩容前旧数组长度小于等于0，但是旧的阈值大于0，则用旧的阈值来作为扩容后的数组长度
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
        	//这里主要用于HashMap首次使用，使用前对数组容量和阈值进行初始化
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        //这里还不太明白，有知道的请告诉我
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        //更新全局阈值
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        	//创建一个新的节点数组
            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        //原数组指向新的数组
        table = newTab;
        //开始处理旧数组中的节点数据
        if (oldTab != null) {
        	//遍历旧的数组
            for (int j = 0; j < oldCap; ++j) {
            	//临时节点，用于节点数据的调整
                Node<K,V> e;
                //如果旧数组中的某个位置上的节点不为空，则需要把该位置上的节点映射到新的数组位置上
                if ((e = oldTab[j]) != null) {
                	//有利于GC
                    oldTab[j] = null;
                    //如果该位置节点的下一个节点为空，说明该位置就一个节点，那么把该位置节点直接放到新数组中，在新数组中的位置是e.hash & (newCap - 1)
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    //如果该位置节点是一个红黑树，那就调整红黑树，使用两颗红黑树进行调整，或者把红黑树再调整为链表    
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                    	//用两个链表来处理旧的链表数据
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            //oldCap对应的二进制一定是0...0001000..0,把可以消掉0的放到一个链表中
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            //消不掉的放到另一个链表中
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                        	//把消掉0的链表所在位置与旧数组位置相同
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                        	//消不掉0的链表所在位置，是旧位置+旧数组长度所在的位置
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```

