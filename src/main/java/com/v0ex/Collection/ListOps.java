package com.v0ex.Collection;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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


        List<Student> l1 = new ArrayList<>();
        l1.add(new Student("zhangsan","18"));
        l1.add(new Student("lisi","20"));
        List<Student> collect = l1.stream().map(student -> {
            student.setNumber("001");
            return student;
        }).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
    }

    static class Student{
        public String name;
        public String age;
        public String number;

        public Student(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
