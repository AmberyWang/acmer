package org.old.wang;

/**
 * @author wangtingting
 * @date 2021/5/11
 */
public class CommonUtil {

    public static void printArray(int[] array, String arrayName) {
        System.out.println(arrayName);
        if (array == null || array.length == 0) {
            System.out.println(arrayName + " is blank, no elements ");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                System.out.println(array[i]);
            } else {
                System.out.print(array[i] + ", ");
            }
        }
    }

    public static int[] copyPreNElement(int[] array, int N) {
        int[] subArray = new int[N];
        for (int i = 0; i < N; i++) {
            subArray[i] = array[i];
        }

        return subArray;
    }

}
