package com.v0ex.lintcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单
 *
 * 插入区间
 * 给出一个无重叠的按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 插入区间[2, 5] 到 [[1,2], [5,9]]，我们得到 [[1,9]]。
 * 插入区间[3, 4] 到 [[1,2], [5,9]]，我们得到 [[1,2], [3,4], [5,9]]。
 */
public class Solution30 {


    public static List<Interval> insert(List<Interval> intervals ,Interval newInterval){
        List<Interval> ans = new ArrayList<>();
        int idx = 0;
        while (idx < intervals.size() && intervals.get(idx).start < newInterval.start ){
            idx++;
        }
        intervals.add(idx,newInterval);
        Interval last = null;
        for (Interval item : intervals){
            if (last == null || last.end < item.start){
                ans.add(item);
                last = item;
            }else {
                last.end = Math.max(last.end,item.end);
            }
        }
        return ans;
    }

    public static List<Interval> insert1(List<Interval> intervals,Interval newInterval){
        if (intervals == null || newInterval == null)return intervals;
        List<Interval> results = new ArrayList<>();
        int insertPos = 0;
        for (Interval interval : intervals){
            if (interval.end < newInterval.start){
                results.add(interval);
                insertPos++;
            }else if (interval.start > newInterval.end){
                results.add(interval);
            }else {
                newInterval.start = Math.min(interval.start,newInterval.start);
                newInterval.end = Math.max(interval.end,newInterval.end);
            }
        }
        results.add(insertPos,newInterval);
        return results;
    }

    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1,2));
        list.add(new Interval(5,9));
        Interval newInterval = new Interval(2,5);
        List<Interval> intervals = insert1(list, newInterval);
        System.out.println(JSON.toJSONString(intervals));
    }

    static class Interval{
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
}
