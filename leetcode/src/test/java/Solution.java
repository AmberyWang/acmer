import org.junit.Test;

/**
 * @author wangtingting
 * @date 2021/5/15
 */
public class Solution {

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        third.next = fourth;
        second.next = third;
        head.next = second;

        reorderList(head);
        System.out.println(head.val);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // 逆序链表
        ListNode first = head;
        ListNode second = head.next;
        ListNode third = head.next.next;
        while (third != null) {
            second.next = first;
            if (first == head) {
                first.next = null;
            }
            first = second;
            second = third;
            third = third.next;
        }
        second.next = first;

        ListNode tail = second;

        ListNode newHeadCurrent = head;
        ListNode newHead = newHeadCurrent;
        head = head.next;

        newHeadCurrent.next = tail;
        newHeadCurrent = newHeadCurrent.next;
        tail = tail.next;

        while (head != tail || !(head.next == tail && tail.next == head)) {
            newHeadCurrent.next = head;
            head = head.next;
            newHeadCurrent = newHeadCurrent.next;

            newHeadCurrent.next = tail;
            tail = tail.next;
            newHeadCurrent = newHeadCurrent.next;
        }
        head = newHead;
    }

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
