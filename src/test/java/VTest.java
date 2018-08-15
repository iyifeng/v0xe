import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * Created by zbj on 18/3/28.
 */
public class VTest {

    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put(null, 0);
        map.put("java", 1);
        map.put("c++", 2);
        map.put("python", 3);
        map.put("php", 4);
        map.put("nodejs", 5);
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("php".hashCode() == "c++".hashCode());
        TreeMap treeMap = new TreeMap();
        HashMap<String,Integer> map2 = new HashMap<>();
        map2.put(null, 0);
        map2.put("java", 1);
        map2.put("c++", 2);
        map2.put("python", 3);
        map2.put("php", 4);
        map2.put("nodejs", 5);

        if (map.equals(map2)){
            System.out.println("=========");
        }

        List<Map<String,Integer>> list1 = new ArrayList<>();
        Set<Map<String,Integer>> list2 = new HashSet<>();
        list1.add(map);
        list1.add(map2);
        list2.add(map);
        list2.add(map2);
        System.out.println(list1.size());
        System.out.println(list2.size());



        for (int i = 0; i < 10; i++) {
            int v = (int)(Math.random() * 10) % 2;
            System.out.println("####"+v);
            System.out.println("^^^^"+Math.random());
        }
        int[] ints = new int[]{4,3,1,2,5};
        try {
            int[] sort = sort(ints);
            System.out.println(JSON.toJSONString(sort));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
            System.out.println("==="+JSON.toJSONString(arr));
        }
        return arr;
    }
}
