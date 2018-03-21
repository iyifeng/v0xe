package com.v0ex.lambda;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by zbj on 17/1/24.
 */
public class LambdaPerson {

    private static final Logger LOGGER = LoggerFactory.getLogger(LambdaPerson.class);

    private long id;

    private long parentId;

    private long taxRate;

    private String status;


    public LambdaPerson(long id, long parentId, long taxRate) {
        this.id = id;
        this.parentId = parentId;
        this.taxRate = taxRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(long taxRate) {
        this.taxRate = taxRate;
    }

    public static void main(String[] args) {
        /**
         * 分组，求和
         */
        List<LambdaPerson> result = new ArrayList<>();
        LambdaPerson l1 = new LambdaPerson(1,0,10);
        LambdaPerson l2 = new LambdaPerson(11,1,2);
        LambdaPerson l3 = new LambdaPerson(12,1,8);
        LambdaPerson l4 = new LambdaPerson(2,0,7);
        LambdaPerson l5 = new LambdaPerson(21,2,3);
        LambdaPerson l6 = new LambdaPerson(22,2,4);
        result.add(l1);
        result.add(l2);
        result.add(l3);
        result.add(l4);
        result.add(l5);
        result.add(l6);
        Map<Long, Long> collect = result.parallelStream()
                .filter(l -> l.getParentId() > 0)
                .collect(Collectors.groupingBy(LambdaPerson::getParentId, Collectors.summingLong(o -> o.getTaxRate())));
        System.out.println(JSON.toJSONString(collect));
        LOGGER.info(JSON.toJSONString(collect));

        /**
         * 更新List
         */
        List<LambdaPerson> newList = result.parallelStream()
                .filter(Objects::nonNull)
                .map(o -> {
                    o.setStatus("Y");
                    return o;
                })
                .collect(Collectors.toList());
        LOGGER.info(JSON.toJSONString(newList));

        /**
         * sort排序
         */
        result.sort(Comparator.comparingLong(LambdaPerson::getTaxRate));
        LOGGER.info("排序以后结果集{}",JSON.toJSONString(result));

        /**
         * 分组排序
         */
        Map<LambdaPerson, Long> collect1 = result.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<LambdaPerson, Long> collect2 = result
                .stream()
                .sorted(Comparator.comparing(LambdaPerson::getTaxRate))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        ArrayList<LambdaPerson> collect3 = result
                .stream()
                .sorted(Comparator.comparing(LambdaPerson::getTaxRate).reversed())
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(LambdaPerson::getParentId))), ArrayList::new));
        LOGGER.info("分组排序以后结果集{}",JSON.toJSONString(collect3));
    }
}
