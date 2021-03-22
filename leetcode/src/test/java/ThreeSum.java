
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 给定数组，3 个数的和大于某个值的概率
 * 公众号 https://mp.weixin.qq.com/s/mlSQdVG_BpBNTBILeqmO7g
 *
 * @author wangtingting
 * @date 2021/3/19
 */
@Slf4j
public class ThreeSum {

    @Test
    public void threeSumProbabilityTest() {
        int[] orderArray1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        double probability1 = countProbability(orderArray1, 9);
        System.out.println(probability1);
        // <=9的集合（1,2,3）(1,2,4)(1,2,5)(1,2,6)(1,3,4)(1,3,5)(2,3,4)
        System.out.println(1 - (7 * 1.0 / cnk(12, 3)));
        Assert.assertEquals(probability1, 1 - (7 * 1.0 / cnk(12, 3)), 0.0000);

        int[] orderArray2 = {1, 2, 3, 4};
        double probability2 = countProbability(orderArray2, 10);
        System.out.println(probability2);
        System.out.println(0.0);
        Assert.assertEquals(probability2, 0.0, 0.0000);

        double probability3 = countProbability(orderArray2, 3);
        System.out.println(probability3);
        System.out.println(1.0);
        Assert.assertEquals(probability3, 1.0, 0.0000);

        int[] orderArray3 = {1, 2, 3, 4, 5, 6};
        double probability4 = countProbability(orderArray3, 11);
        System.out.println(probability4);
        double actual = 7.0 / cnk(6, 3);
        System.out.println(actual);//<6,5,1> <6,5,2><6,5,3><6,5,4><6,4,2><6,4,3><5,4,3>
        Assert.assertEquals(probability4, actual, 0.0000);
    }

    @Test
    public void twoSumTest() {
        int[] orderArray = {1, 2, 3, 4, 6, 10, 16};
        Assert.assertEquals(computeCountOfTwoNumSumNotMoreThan(orderArray, 17, Lists.newArrayList()), 16);
        Assert.assertEquals(computeCountOfTwoNumSumNotMoreThan(orderArray, 1, Lists.newArrayList()), 0);
        Assert.assertEquals(computeCountOfTwoNumSumNotMoreThan(orderArray, 3, Lists.newArrayList()), 1);
        Assert.assertEquals(computeCountOfTwoNumSumNotMoreThan(orderArray, 4, Lists.newArrayList()), 2);
        Assert.assertEquals(computeCountOfTwoNumSumNotMoreThan(orderArray, 26, Lists.newArrayList()), 21);

        int[] orderArray1 = {1, 2, 3};
        Assert.assertEquals(computeCountOfTwoNumSumNotMoreThan(orderArray1, 6, Lists.newArrayList()), 3);
    }

    @Test
    public void threeSumTest() {
        int[] orderArray = {1, 2, 3, 4, 5, 6};
        Assert.assertEquals(computeCountOfThreeNumSumNotMoreThan(orderArray, 5), 0);
        Assert.assertEquals(computeCountOfThreeNumSumNotMoreThan(orderArray, 6), 1);
        Assert.assertEquals(computeCountOfThreeNumSumNotMoreThan(orderArray, 7), 2);
        Assert.assertEquals(computeCountOfThreeNumSumNotMoreThan(orderArray, 8), 4);
        Assert.assertEquals(computeCountOfThreeNumSumNotMoreThan(orderArray, 9), 7);
        Assert.assertEquals(computeCountOfThreeNumSumNotMoreThan(orderArray, 10), 10);
        Assert.assertEquals(computeCountOfThreeNumSumNotMoreThan(orderArray, 11), 13);
        Assert.assertEquals(computeCountOfThreeNumSumNotMoreThan(orderArray, 12), 16);

        int[] orderArray1 = {1, 3, 4, 5, 6, 7, 8};
        Assert.assertEquals(computeCountOfThreeNumSumNotMoreThan(orderArray1, 12), 10);
    }

    @Test
    public void test() {
        int[] array = {1, 2};
        List<Pair> list = cn2(array);
        System.out.println(list.size());
    }

    /**
     * 使用双指针法计算有序数组，3 个数的和大于 target 的概率
     * 时间复杂度 O（NlogN）
     *
     * @return
     */
    private double countProbability(int[] orderArray, int target) {
        int threeSumNotMoreThanTargetCount = computeCountOfThreeNumSumNotMoreThan(orderArray, target);
        return 1 - (threeSumNotMoreThanTargetCount * 1.0 / cnk(orderArray.length, 3));
    }

    private int threeAdd(int a, int b, int c) {
        return a + b + c;
    }

    private int computeCountOfThreeNumSumNotMoreThan(int[] orderArray, int target) {

        if (orderArray.length < 3) {
            return 0;
        }
        if (orderArray[0] > target) {
            return 0;
        }
        if (orderArray.length == 3) {
            if (threeAdd(orderArray[0], orderArray[1], orderArray[2]) <= target) {
                return 1;
            } else {
                return 0;
            }
        }

        if (threeAdd(orderArray[0], orderArray[1], orderArray[2]) > target) {
            return 0;
        }

        int tail = orderArray.length - 1;
        for (int i = orderArray.length - 1; i >= 2; i--) {
            tail = i;
            if (threeAdd(orderArray[i], orderArray[0], orderArray[1]) <= target) {
                break;
            }
        }

        if (tail == 2) {
            if (threeAdd(orderArray[0], orderArray[1], orderArray[2]) <= target) {
                return 1;
            } else {
                return 0;
            }
        }

        /************* 初始条件判断结束，找到临界元素 ****************************/

        List<String> threeResultList = Lists.newArrayList();
        List<String> threeDumplicateList = Lists.newArrayList();
        int threeSumNotMoreThanTargetCount = 0;
        int head = 0;
        for (int i = tail; i >= 2; i--) {

            Pair pair = xxxThreeCompute(orderArray, head, i, target, threeSumNotMoreThanTargetCount, false,
                    threeResultList, threeDumplicateList);

            head = pair.getK();
            threeSumNotMoreThanTargetCount = pair.getV();

            if (head == i) {
                break;
            }
        }

        System.out.println("count:" + threeSumNotMoreThanTargetCount);
        System.out.println("threeResultList " + threeResultList);
        System.out.println("threeDumplicateList " + threeDumplicateList);

        return threeSumNotMoreThanTargetCount;
    }

    private Pair xxxThreeCompute(int[] orderArray, int head, int tail, int target, int count,
                                 boolean isRecursived, List<String> resultListOfThree, List<String> dumplicateListOfThree) {
        if (head == tail || head + 1 == tail) {
            return new Pair(head, count);
        }

        // 尾指针移动
        if (!isRecursived) {

            // 如果 [head] + [head + 1] + [tail] 小于等于 target
            if (threeAdd(orderArray[head], orderArray[head + 1], orderArray[tail]) <= target) {

                // 1. 说明 [0,head+1] 区间的元素任选2个，与 [tail]元素相加都小于 target
                count += cnk(head + 2, 2);
                // TODO
                int[] subArray1 = Arrays.copyOf(orderArray, head + 2);
                List<Pair> pairList1 = cn2(subArray1);
                for (Pair pair : pairList1) {
                    addElementOfThree(orderArray[tail], pair.getK(), pair.getV(), resultListOfThree, dumplicateListOfThree);
                }

                // 2. 头指针移动
                return xxxThreeCompute(orderArray, head + 1, tail, target, count, true, resultListOfThree, dumplicateListOfThree);
            }

            // 如果 [head] + [head + 1] + [tail] 大于 target
            else {

                // 1. 选定[tail]
                // 1.1 从临界条件，一个往大了找，一个往小了找
                // x ∈  [head+1, tail-1] 升序遍历
                // j ∈ [head-1,0] 倒序遍历，如果找到第一个元素 ，[j] + [x] + [tail] <= target，则说明指定x的情况下，有 [0, j] 个元素都满足条件
                for (int x = head + 1; x < tail; x++) {
                    for (int j = head - 1; j >= 0; j--) {
                        if (threeAdd(orderArray[j], orderArray[x], orderArray[tail]) <= target) {
                            count += j + 1;
                            // TODO
                            for (int k = j; k >= 0; k--) {
                                addElementOfThree(orderArray[tail], orderArray[x], orderArray[k], resultListOfThree, dumplicateListOfThree);
                            }
                            break;
                        }
                    }
                }
                // 1.1 从 [0, head] 中寻找2个数 + [tail] <= target
                int[] subArray = Arrays.copyOf(orderArray, head + 1);
                List<String> resultListOfTwo = Lists.newArrayList();
                count += computeCountOfTwoNumSumNotMoreThan(subArray, target - orderArray[tail], resultListOfTwo);
                for (String str : resultListOfTwo) {
                    int a = Integer.valueOf(str.split(",")[0]);
                    int b = Integer.valueOf(str.split(",")[1]);
                    addElementOfThree(orderArray[tail], a, b, resultListOfThree, dumplicateListOfThree);
                }


                // 2. 尾指针移动
                return new Pair(head, count);
            }
        }
        // 头指针移动
        else {
            // 2. 选定 [head + 1]，从 [0,head] 区间的元素任选 2 个元素，相加都会小于target
            count += cnk(head + 1, 2);
            // TODO
            int[] subArray2 = Arrays.copyOf(orderArray, head + 1);
            List<Pair> pairList2 = cn2(subArray2);
            for (Pair pair : pairList2) {
                addElementOfThree(orderArray[head + 1], pair.getK(), pair.getV(), resultListOfThree, dumplicateListOfThree);
            }


            // 如果 [head] + [head + 1] + [tail] 小于等于 target
            if (threeAdd(orderArray[head], orderArray[head + 1], orderArray[tail]) <= target) {

                // 1. 选定 [tail] 和 [head + 1] , [0, head] 共 head + 1 个元素中任意选择一个都满足条件
                count += head + 1;
                // TODO
                for (int j = head; j >= 0; j--) {
                    addElementOfThree(orderArray[tail], orderArray[head + 1], orderArray[j], resultListOfThree, dumplicateListOfThree);
                }


                // 3. 移动头指针
                return xxxThreeCompute(orderArray, head + 1, tail, target, count, true,
                        resultListOfThree, dumplicateListOfThree);
            }

            // 如果 [head] + [head + 1] + [tail] 大于 target
            else {

                // 1.
                // 选定 [tail]
                // x ∈  [head+1, tail-1] 升序遍历
                // j ∈ [head-1,0] 倒序遍历，如果找到第一个元素 ，[j] + [x] + [tail] <= target，则说明指定x的情况下，有 [0, j] 个元素都满足条件
                for (int x = head + 1; x < tail; x++) {
                    for (int j = head - 1; j >= 0; j--) {
                        if (threeAdd(orderArray[j], orderArray[x], orderArray[tail]) <= target) {
                            count += j + 1;
                            // TODO
                            for (int k = j; k >= 0; k--) {
                                addElementOfThree(orderArray[tail], orderArray[x], orderArray[k], resultListOfThree, dumplicateListOfThree);
                            }
                            break;
                        }
                    }
                }


                // 2. 尾指针移动
                return new Pair(head, count);
            }
        }

    }

    private void addElementOfThree(int a, int b, int c, List<String> resultList, List<String> dumpliateList) {
        String newStr = a + "," + b + "," + c;

        String aStr = String.valueOf(a);
        String bStr = String.valueOf(b);
        String cStr = String.valueOf(c);
        for (String str : resultList) {
            if (str.contains(aStr) && str.contains(bStr) && str.contains(cStr)) {
                dumpliateList.add(newStr);
                break;
            }
        }

        resultList.add(newStr);
    }

    private int computeCountOfTwoNumSumNotMoreThan(int[] orderArray, int target, List<String> resultList) {
        if (orderArray.length < 2) {
            return 0;
        }
        if (orderArray.length == 2) {
            if (orderArray[0] + orderArray[1] > target) {
                return 0;
            } else {
                addElement(orderArray[0], orderArray[1], resultList, Lists.newArrayList());
                return 1;
            }
        }

        int head = 0;
        int tail = orderArray.length - 1;
        for (int i = orderArray.length - 1; i >= 0; i--) {
            tail = i;
            if (orderArray[0] + orderArray[i] <= target) {
                break;
            }
        }

        int count = 0;
        List<String> dumplicateList = Lists.newArrayList();
        // 直到找到一个尾元素和最小的头元素相加都小于 target
        for (int i = tail; i >= head; i--) {
            Pair pair = xxxTwoCompute(head, i, orderArray, target, count, false, resultList, dumplicateList);
            head = pair.getK();
            count = pair.getV();

        }

        System.out.println("resultList: " + resultList);
        System.out.println("dumplicateList: " + dumplicateList);

        return count;
    }

    /**
     * @param head
     * @param tail
     * @param orderArray
     * @param target
     * @param count
     * @param isRecursived 是否是递归调用，隐含逻辑：递归调用就是头指针移动，否则就是尾指针移动
     * @return
     */
    private Pair xxxTwoCompute(int head, int tail, int[] orderArray, int target, int count, boolean isRecursived
            , List<String> resultList, List<String> dumplicateList) {
        if (head == tail) {
            return new Pair(head, count);
        }
        // 如果是头指针在移动，尾指针并未移动
        if (isRecursived) {

            //如果第[head]个元素与[tail]个元素之和，小于等于target
            if (orderArray[head] + orderArray[tail] <= target) {

                // 第1种情况需要计算：只计算[head]元素跟[tail]元素作为一个组合
                count++;
                // TODO
                addElement(orderArray[head], orderArray[tail], resultList, dumplicateList);

                // 第2种情况需要计算：以[head]元素为一个元素，则与[0, head -1] 的元素之和，都满足条件
                count += head;
                // TODO
                for (int j = head - 1; j >= 0; j--) {
                    addElement(orderArray[head], orderArray[j], resultList, dumplicateList);
                }

                // 第3种情况需要计算：继续头指针移动计算
                return xxxTwoCompute(head + 1, tail, orderArray, target, count, true, resultList, dumplicateList);
            }

            // 如果[head]元素与[tail]元素之和，大于target
            else {

                // 第1种情况需要计算：以[head]元素为满足条件的二元组合中的一个，降序查找[head-1，0]的元素第一个与[head]元素之和满足条件的，
                // 如果这个索引是 j，则说明有 j+1 个元素都满足条件
                if (head >= 1) {
                    for (int j = head - 1; j >= 0; j--) {
                        if (orderArray[j] + orderArray[head] <= target) {
                            count += j + 1;

                            // TODO
                            for (int k = j; k >= 0; k--) {
                                addElement(orderArray[head], orderArray[k], resultList, dumplicateList);
                            }

                            break;
                        }
                    }
                }

                // 第2种情况需要计算：尾指针移动计算
                return new Pair(head, count);

            }
        }
        // 如果是尾指针在移动，头指针未动
        else if (!isRecursived) {

            // 如果[head]元素与[tail]元素之和，小于等于target
            if (orderArray[head] + orderArray[tail] <= target) {

                // 第1种情况需要计算:说明第[0，head]个元素跟[tail]的元素之和，都满足条件
                count += head + 1;
                // TODO
                for (int j = head; j >= 0; j--) {
                    addElement(orderArray[tail], orderArray[j], resultList, dumplicateList);
                }

                // 第2种情况需要计算：头指针移动计算
                return xxxTwoCompute(head + 1, tail, orderArray, target, count, true, resultList, dumplicateList);
            }
            // 如果[head]元素与[tail]元素之和，大于target
            else {

                // 第1种情况需要计算：以[tail]元素为满足条件的二元组合中的一个，
                // 另一个从区间[head - 1, 0]的元素，降序查找、判断
                // 如果第一个元素是索引 j，则说明[j,0]的元素都满足，则有 j+1 个元素
                if (head >= 1) {
                    for (int j = head - 1; j >= 0; j--) {
                        if (orderArray[tail] + orderArray[j] <= target) {
                            count += j + 1;
                            // TODO
                            for (int k = j; k >= 0; k--) {
                                addElement(orderArray[tail], orderArray[k], resultList, dumplicateList);
                            }
                            break;
                        }
                    }
                }

                // 第2中情况需要计算：尾指针移动
                return new Pair(head, count);
            }
        }

        return new Pair(head, count);
    }

    private void addElement(int a, int b, List<String> resultList, List<String> dumplicateList) {
        String combinationStr1 = a + "," + b;
        String combinationStr2 = b + "," + a;
        if (resultList.contains(combinationStr1) || resultList.contains(combinationStr2)) {
            dumplicateList.add(combinationStr1);
        }
        resultList.add(combinationStr1);
    }

    @Getter
    @Setter
    private class Pair<K, V> {
        int K;
        int V;

        public Pair(int K, int V) {
            this.K = K;
            this.V = V;
        }
    }

    /**
     * 从 n 个数中取出 k 个数的组合数
     */
    private int cnk(int n, int k) {
        if (n < k) {
            log.error("非法输入参数 n 和 k", new IllegalArgumentException());
            return 0;
        }
        if (k == 0 || n == k) {
            return 1;
        }

        if (k > n / 2) {
            k = n - k;
        }

        return cnk(n - 1, k) + cnk(n - 1, k - 1);
    }

    private List<Pair> cn2(int[] array) {
        List<Pair> list = Lists.newArrayList();
        if (array.length < 2) {
            return list;
        }
        if (array.length == 2) {
            list.add(new Pair(array[0], array[1]));
            return list;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                list.add(new Pair(array[i], array[j]));
            }
        }
        return list;
    }
}
