package org.old.wang;

import java.util.Random;

/**
 * 给定一个很大很大的数组，求出第 N 大的数是哪个
 *
 * @author wangtingting
 * @date 2021/5/11
 */
public class NthMaxNum {

    public static void main(String[] args) throws Exception {

        int[] array1 = initArray(100);
        int[] array2 = initArray(10);
        CommonUtil.printArray(array1, "array1");
        CommonUtil.printArray(array2, "array2");

        System.out.println("array1 10th max num is " + getNthMaxNum(array1, 10));
        System.out.println("array1 98th max num is " + getNthMaxNum(array1, 98));

        System.out.println("array2 2th max num is " + getNthMaxNum(array2, 2));
        System.out.println("array2 7th max num is " + getNthMaxNum(array2, 7));

        // 参考排序
        int[] sortedArray1 = Heap.maxSort(array1);
        int[] sortedArray2 = Heap.maxSort(array2);
        CommonUtil.printArray(sortedArray1, "sortedArray1");
        CommonUtil.printArray(sortedArray2, "sortedArray2");

        // 非法测试
        System.out.println("101th max num is " + getNthMaxNum(array1, 101));
        System.out.println("-1th max num is " + getNthMaxNum(array1, -1));
    }

    private static int getNthMaxNum(int[] array, int Nth) throws Exception {

        // 边缘判断
        if (array == null || array.length == 0) {
            throw new Exception("array is not valid.");
        }

        if (Nth > array.length || Nth < 1) {
            throw new Exception("Nth param is no correct.");
        }

        if (array.length == 1 && Nth == 1) {
            return array[0];
        }

        // 如果 Nth 小于等于 大数组的一半，则使用最小堆，从大数组中取出 Nth 个最大的元素中最小的一个
        if (Nth <= (array.length / 2)){
            return useMinHeap(array, Nth);
        }
        // 如果 Nth 大于 大数组的一半，则使用最大堆，从大数组中取出 array.length - Nth + 1 个最小的元素中最大的一个
        else {
            return useMaxHeap(array, array.length - Nth + 1);
        }
    }

    private static int useMinHeap(int[] array, int Nth) {

        // 取前 N 个元素
        int[] result = CommonUtil.copyPreNElement(array, Nth);

        // 构建最小堆
        MinHeap.buildMinHeap(result);

        for (int i = Nth; i < array.length; i++) {

            if (array[i] <= result[0]) {
                continue;
            } else {
                result[0] = array[i];
                MinHeap.heapifyOfMin(result, 1);
            }
        }

        return result[0];
    }

    private static int useMaxHeap(int[] array, int Nth) {

        int[] subArray = CommonUtil.copyPreNElement(array, Nth);

        Heap.buildMaxHeap(subArray);

        for (int i = Nth; i < array.length; i++) {

            if (array[i] >= subArray[0]) {
                continue;
            } else {
                subArray[0] = array[i];
                Heap.heapifyOfMax(subArray, 1);
            }
        }

        return subArray[0];
    }


    private static int[] initArray(int size) {
        int[] array = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }

        return array;
    }

}
