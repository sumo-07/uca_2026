package classwork;
import java.util.Stack;

public class minStack {

    // My solution
    /*
     * public static class MinStack {
     * Stack<Integer> stack;
     * int min;
     * 
     * public MinStack() {
     * stack= new Stack<>();
     * 
     * }
     * 
     * void push(int val) {
     * if(stack.size() == 0) {
     * stack.push(val);
     * min= val;
     * }
     * else if(val < min) {
     * stack.push(2 * val - min);
     * min= val;
     * }
     * else {
     * stack.push(val);
     * }
     * }
     * 
     * void pop() {
     * if(stack.size() == 0) {
     * System.out.println("Stack empty");
     * return;
     * }
     * int val= stack.pop();
     * if(val > min) {
     * System.out.println(val);
     * }
     * else {
     * System.out.println(min);
     * min= 2 * min - val;
     * }
     * 
     * }
     * 
     * int top() {
     * if(stack.size() == 0) {
     * System.out.println("Stack empty");
     * return -1;
     * }
     * int val= stack.peek();
     * if(val < min) {
     * return min;
     * }
     * return val;
     * }
     * 
     * int getMin() {
     * if(stack.size() == 0) {
     * System.out.println("Stack is empty");
     * return 0;
     * }
     * return min;
     * }
     * }
     * 
     * public static void main(String[] args) {
     * MinStack minStack= new MinStack();
     * 
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * 
     * System.out.println(minStack.getMin());
     * 
     * minStack.pop();
     * 
     * System.out.println(minStack.top());
     * 
     * System.out.println(minStack.getMin());
     * }
     * 
     */

    static class MinStack {

        class Node {
            int data;
            Node next;
            int currMin;

            Node(int data) {
                this.data = data;
                this.currMin = data;
            }
        }

        Node head;

        public MinStack() {
            head = null;
        }

        public void push(int val) {
            Node newNode = new Node(val);

            if (head != null) {
                newNode.next = head;
                newNode.currMin = Math.min(newNode.currMin, head.currMin);
            }

            head = newNode;
        }

        public void pop() {
            validate();
            head = head.next;
        }

        public int top() {
            validate();
            return head.data;
        }

        public int getMin() {
            validate();
            return head.currMin;
        }

        private void validate() {
            try{
                if (head == null) {
                    throw new Exception("No element is present");
                }

            }
            catch(Exception err) {
                System.out.println(err.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        MinStack minStack= new MinStack();
        
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println(minStack.getMin());

        minStack.pop();

        System.out.println(minStack.top()); 

        System.out.println(minStack.getMin());
    }
}
