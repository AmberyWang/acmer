import org.junit.Test;

/**
 * @author wangtingting
 * @date 2021/5/16
 */
public class ReorderList {

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        fourth.next = fifth;
        third.next = fourth;
        second.next = third;
        head.next = second;

        reorderList(head);
        System.out.println(head.next.val);
    }

    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return;
        }

        // 1 寻找中间节点
        ListNode walkOne = head;
        ListNode walkTwo = head;
        while(walkTwo != null && walkTwo.next != null){
            walkOne = walkOne.next;
            walkTwo = walkTwo.next.next;
        }

        ListNode reverseStart = walkOne.next;
        walkOne.next = null;
        ListNode reverseHead = null;

        // 2 逆序[mid+1，end]链表
        if(reverseStart.next == null){
            reverseHead = reverseStart;
        } else if(reverseStart.next.next == null){
            reverseHead = reverseStart.next;
            reverseHead.next = reverseStart;
            reverseStart.next = null;
        } else {
            ListNode first = reverseStart;
            ListNode second = reverseStart.next;
            ListNode third = reverseStart.next.next;
            while(third != null) {
                if(first == reverseStart) {
                    first.next = null;
                }
                second.next = first;
                first = second;
                second = third;
                third = third.next;
            }
            second.next = first;
            reverseHead = second;
        }

        // 3 合并链表
        ListNode newHead = head;
        ListNode newHeadCurrent = head;
        head = head.next;
        newHeadCurrent.next = reverseHead;
        newHeadCurrent = newHeadCurrent.next;
        reverseHead = reverseHead.next;

        while(head != null && reverseHead != null){
            newHeadCurrent.next = head;
            newHeadCurrent = newHeadCurrent.next;
            head = head.next;

            newHeadCurrent.next = reverseHead;
            newHeadCurrent = newHeadCurrent.next;
            reverseHead = reverseHead.next;
        }

        if(head != null) {
            newHeadCurrent.next = head;
        }

        if(reverseHead != null) {
            newHeadCurrent.next = reverseHead;
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
