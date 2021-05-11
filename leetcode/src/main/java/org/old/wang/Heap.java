package org.old.wang;

import java.lang.reflect.Array;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangtingting
 * @date 2021/5/11
 */
@Slf4j
public class Heap {

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

        array1 = maxSort(array1);
        array2 = maxSort(array2);
        array3 = maxSort(array3);
        array4 = maxSort(array4);
        array5 = maxSort(array5);
        array6 = maxSort(array6);

        System.out.println("--------- after max sort ---------");
        CommonUtil.printArray(array1, "array1");
        CommonUtil.printArray(array2, "array2");
        CommonUtil.printArray(array3, "array3");
        CommonUtil.printArray(array4, "array4");
        CommonUtil.printArray(array5, "array5");
        CommonUtil.printArray(array6, "array6");
    }




    /********* 从大到小排序 ****************/
    public static int[] maxSort(int[] array) {

        // 边界条件判断
        if (array == null || array.length == 0 || array.length == 1) {
            return array;
        }

        buildMaxHeap(array);

        return maxHeapSort(array);

    }

    public static void buildMaxHeap(int[] array) {
        int heapifyMaxCount = array.length / 2;
        for (int i = heapifyMaxCount; i >= 1; i--) {
            heapifyOfMax(array, i);
        }
    }

    public static void heapifyOfMax(int[] array, int heapIndex) {
        int leftIndex = 2 * heapIndex;
        int rightIndex = leftIndex + 1;

        // 边界判断
        if (leftIndex > array.length) {
            return;
        }

        // 比较最大的元素
        int maxIndex = heapIndex;
        if (array[leftIndex - 1] > array[heapIndex - 1]) {
            maxIndex = leftIndex;
        }

        if (rightIndex <= array.length && array[rightIndex - 1] > array[maxIndex - 1]) {
            maxIndex = rightIndex;
        }

        // exchange
        if (maxIndex != heapIndex) {

            int temp = array[heapIndex - 1];
            array[heapIndex - 1] = array[maxIndex - 1];
            array[maxIndex - 1] = temp;

            heapifyOfMax(array, maxIndex);
        }

    }

    private static int[] maxHeapSort(int[] array) {
        int[] sortedArray = new int[array.length];

        int index = 0;
        int arrayLength = array.length;
        int lastElementIndex = array.length - 1;

        while (arrayLength >= 2) {
            sortedArray[index] = array[0];
            index++;

            // exchange last to first
            array[0] = array[arrayLength - 1];

            arrayLength = arrayLength - 1;

            // 数组移除最后一个元素后，调整成最大堆
            int[] newArray = arrayRemoveLastElement(array);
            heapifyOfMax(newArray, 1);
            array = newArray;
        }

        sortedArray[lastElementIndex] = array[0];

        return sortedArray;
    }

    private static int[] arrayRemoveLastElement(int[] array) {
        if (array == null || array.length == 0 || array.length == 1) {
            return new int[0];
        }

        int[] newArray = new int[array.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }

        return newArray;
    }

}
