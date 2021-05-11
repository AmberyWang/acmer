package org.old.wang;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * @author wangtingting
 * @date 2021/5/11
 */
public class MinHeap {

    public static void main(String[] args) {
        // 1. 数组静态初始化
        int[] array1 = new int[]{9};
        int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // 2. 动态初始化
        int[] array3 = new int[5];
        Random random = new Random();
        for (int i = 0; i < array3.length; i++) {
            array3[i] = random.nextInt(100);
        }

        // 3. 反射
        int[] array4 = (int[]) Array.newInstance(int.class, 20);
        for (int i = 0; i < array4.length; i++) {
            array4[i] = random.nextInt(1000);
        }

        // 边缘测试
        int[] array5 = null;
        int[] array6 = new int[0];

        System.out.println("--------- init array ---------");
        CommonUtil.printArray(array1, "array1");
        CommonUtil.printArray(array2, "array2");
        CommonUtil.printArray(array3, "array3");
        CommonUtil.printArray(array4, "array4");
        CommonUtil.printArray(array5, "array5");
        CommonUtil.printArray(array6, "array6");

        array1 = minSort(array1);
        array2 = minSort(array2);
        array3 = minSort(array3);
        array4 = minSort(array4);
        array5 = minSort(array5);
        array6 = minSort(array6);

        System.out.println("--------- after min sort ---------");
        CommonUtil.printArray(array1, "array1");
        CommonUtil.printArray(array2, "array2");
        CommonUtil.printArray(array3, "array3");
        CommonUtil.printArray(array4, "array4");
        CommonUtil.printArray(array5, "array5");
        CommonUtil.printArray(array6, "array6");
    }


    /********* 从小到大排序 ****************/

    public static int[] minSort(int[] array) {
        // 边界条件判断
        if (array == null || array.length == 0 || array.length == 1) {
            return array;
        }

        buildMinHeap(array);

        return minHeapSort(array);
    }

    public static void buildMinHeap(int[] array) {
        for (int i = array.length / 2; i >= 1; i--) {
            heapifyOfMin(array, i);
        }
    }

    public static void heapifyOfMin(int[] array, int parentElementIndex) {
        int leftChildIndex = parentElementIndex * 2;
        int rightChildIndex = parentElementIndex * 2 + 1;

        if (leftChildIndex > array.length) {
            return;
        }

        int minIndex = parentElementIndex;
        if (array[leftChildIndex - 1] < array[parentElementIndex - 1]) {
            minIndex = leftChildIndex;
        }

        if (rightChildIndex <= array.length && array[rightChildIndex - 1] < array[minIndex - 1]) {
            minIndex = rightChildIndex;
        }

        if (minIndex != parentElementIndex) {
            exchange(array, parentElementIndex - 1, minIndex - 1);
            heapifyOfMin(array, minIndex);
        }
    }

    private static void exchange(int[] array, int indexA, int indexB) {
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }


    private static int[] minHeapSort(int[] array) {

        int arrayLength = array.length;
        int[] result = new int[arrayLength];
        int index = 0;
        int arrayDynamicLength = arrayLength;
        while (arrayDynamicLength >= 2) {

            // 最小堆的第一个元素是最小的
            result[index] = array[0];
            index++;

            // exchange
            exchange(array, 0, arrayDynamicLength - 1);

            arrayDynamicLength = arrayDynamicLength - 1;
            int[] newArray = CommonUtil.copyPreNElement(array, array.length - 1);
            heapifyOfMin(newArray, 1);
            array = newArray;
        }

        result[arrayLength - 1] = array[arrayDynamicLength - 1];

        return result;
    }


}
