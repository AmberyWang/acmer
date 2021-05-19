package org.old.wang;

/**
 * @author wangtingting
 * @date 2021/5/19
 */
public class LeastOfRotateArray {

    public static int minArray(int[] numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }

        int start = 0;
        int end = numbers.length - 1;

        if (numbers[start] < numbers[end]) {
            return numbers[start];
        }

        int mid = start;
        while (numbers[start] >= numbers[end]) {
            if (end - start == 1) {
                return numbers[end];
            }

            mid = (start + end) / 2;
            if (numbers[start] == numbers[mid] && numbers[mid] == numbers[end]) {
                return getMinByScanAll(numbers, start, end);
            }

            if (numbers[mid] >= numbers[start]) {
                start = mid;
            }
            if (numbers[mid] <= numbers[end]) {
                end = mid;
            }
        }
        return numbers[mid];
    }

    private static int getMinByScanAll(int[] numbers, int start, int end) {
        int min = numbers[start];
        for (int i = start + 1; i <= end; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }


    public static void main(String[] args) {
        int[] numbers = new int[]{-1, -1, -1, -1};
        int min = minArray(numbers);
    }

}
