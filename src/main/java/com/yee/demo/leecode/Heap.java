package com.yee.demo.leecode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/17 2:35 PM
 */
public class Heap {


    /**3
     * 最小堆
     * @param oldArr
     * @param k
     * @return
     */
    public static ArrayList<Integer> minHeap(Integer[] oldArr, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int length = oldArr.length;
        if(k > length || k == 0){
            return result;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < length; i++) {
            if (minHeap.size() != k) {
                minHeap.offer(oldArr[i]);
            } else if (minHeap.peek() > oldArr[i]) {
                Integer temp = minHeap.poll();
                temp = null;
                minHeap.offer(oldArr[i]);
            }
        }
        for (Integer integer : minHeap) {
            result.add(integer);
        }
        return result;

    }

    /**
     * 最大堆
     * @param oldArr
     * @param k
     * @return
     */
    public static ArrayList<Integer> maxHeap(Integer[] oldArr, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int length = oldArr.length;
        if(k > length || k == 0){
            return result;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < length; i++) {
            Integer peek = maxHeap.peek();
            Integer nowValue = oldArr[i];
            if (maxHeap.size() != k) {
                if (peek == null){
                    maxHeap.offer(nowValue);
                }else if (peek != null && peek < nowValue){
                    maxHeap.offer(nowValue);
                }
            } else {
                if ( peek < nowValue) {
                    Integer temp = maxHeap.poll();
                    temp = null;
                    maxHeap.offer(nowValue);

                }
            }
        }
        for (Integer integer : maxHeap) {
            result.add(integer);
        }
        return result;
    }
}
