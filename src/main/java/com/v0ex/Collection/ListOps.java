package com.v0ex.Collection;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by zbj on 16/2/2.
 */
public class ListOps {

    public static void main(String[] args) {
        List<String> aList = Arrays.asList("a", "b", "c", "d");
        List<String> bList = Arrays.asList("b", "c", "r", "e", "u");
        Collection intersection = CollectionUtils.intersection(aList, bList);
        List<String> result =  new ArrayList<>(intersection);
        System.out.println(JSON.toJSONString(result));

//        aList.retainAll(bList);
//        System.out.println(JSON.toJSONString(aList));
        List<String> cList = new ArrayList<>();
        Collection intersection1 = CollectionUtils.intersection(aList, cList);
        List<String> cResult = new ArrayList<>();
        System.out.println(JSON.toJSONString(cResult));
    }
}
