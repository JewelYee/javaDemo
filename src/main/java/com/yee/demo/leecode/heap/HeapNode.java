package com.yee.demo.leecode.heap;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/17 6:39 PM
 */
public class HeapNode {


    private int size;//堆大小
    private int[] heap;//保存堆数组

    //初始化堆
    public HeapNode(int n) {
        heap = new int[n];
        size = 0;
    }

    //小顶堆建堆
    public void minInsert(int key){
        int i = this.size;
        if (i==0) {
            heap[0] = key;
        }else {
            while (i>0 && heap[i/2]>key){
                heap[i] = heap[i/2];
                i = i/2;
            }
            heap[i] = key;
        }
        this.size++;
    }

    //大顶堆建堆
    public void maxInsert(int key){
        int i = this.size;
        if (i==0) heap[0] = key;
        else {
            while (i>0 && heap[i/2]<key){
                heap[i] = heap[i/2];
                i = i/2;
            }
            heap[i] = key;
        }
        this.size++;
    }

    //小顶堆删除
    public int minDelete(){
        if (this.size==0) return -1;
        int top = heap[0];
        int last = heap[this.size-1];
        heap[0] = last;
        this.size--;
        //堆化
        minHeapify(0);
        return top;
    }

    //大顶堆删除
    public int maxDelete(){
        if (this.size==0) return -1;
        int top = heap[0];
        int last = heap[this.size-1];
        heap[0] = last;
        this.size--;
        //堆化
        maxHeapify(0);
        return top;
    }

    //小顶堆化
    public void minHeapify(int i){
        int L = 2*i,R=2*i+1,min;
        if (L<=size && heap[L] < heap[i]) min = L;
        else min = i;
        if (R <= size && heap[R] < heap[min]) min = R;
        if (min!=i){
            int t = heap[min];
            heap[min] = heap[i];
            heap[i] = t;
            minHeapify(min);
        }
    }

    //大顶堆化
    public void maxHeapify(int i){
        int L = 2*i,R=2*i+1,max;
        if (L<=size && heap[L] > heap[i]) max = L;
        else max = i;
        if (R <= size && heap[R] > heap[max]) max = R;
        if (max!=i){
            int t = heap[max];
            heap[max] = heap[i];
            heap[i] = t;
            maxHeapify(max);
        }
    }

    //输出堆
    public void print(){
        for (int i = 0; i < this.size; i++) {
            System.out.print(heap[i]+" ");
        }
        System.out.println();
    }
}
