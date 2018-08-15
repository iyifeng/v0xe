package com.v0ex.Collection;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zbj
 * @date 17/7/12
 */
public class MapOps {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("one","this is 1");
        map.put("two","this is 2");
        List<Map> list = new ArrayList<>();
        List<Map> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String,String> temp = new HashMap<>();
            temp = map;
            temp.replace("two",""+i);
            System.out.println("========="+i+"==="+ JSON.toJSONString(map));
            list.add(temp);
            Map<String,String> t2 = new HashMap<>();
            t2.putAll(map);
            t2.replace("two",""+i);
            list2.add(t2);

        }
        System.out.println(JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(list2));
    }
}
