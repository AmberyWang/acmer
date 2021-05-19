import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author wangtingting
 * @date 2021/5/16
 */
public class MyStack {

    ArrayBlockingQueue<Integer> queue1;
    ArrayBlockingQueue<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new ArrayBlockingQueue<Integer>(100);
        queue2 = new ArrayBlockingQueue<Integer>(100);
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue1.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(queue2.size() == 1) {
            return queue2.poll();
        }

        while(queue1.size() != 1) {
            queue2.add(queue1.poll());
        }
        int result = queue1.poll();
        while(queue2.size() != 0) {
            queue1.add(queue2.poll());
        }
        return result;
    }

    /** Get the top element. */
    public int top() {
        if(queue2.size() == 1) {
            return queue2.poll();
        }

        while(queue1.size() != 1) {
            queue2.add(queue1.poll());
        }
        int result = queue1.peek();
        queue2.add(queue1.poll());
        while(queue2.size() != 0) {
            queue1.add(queue2.poll());
        }
        return result;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.size() == 0 && queue2.size() == 0;
    }
}
