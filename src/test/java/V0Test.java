import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zbj
 * @date 17/8/21
 */
public class V0Test {

    public static void main(String[] args) {
        String k = "$201808$:1004:*3232*";
        String sceneId = k.substring(k.indexOf(":")+1, k.lastIndexOf(":"));
        System.out.println(sceneId);

        double i = 5;
        double j = 3;
        System.out.println(j/i);

        Queue queue = new LinkedList();
    }
}
