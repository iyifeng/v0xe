import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    }
}
