public class reverseLL {
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static Node reverseList(Node head) {
        
        if (head == null || head.next == null) {
            return head;
        }

        
        Node newHead = reverseList(head.next);

        
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    
    public static Node createList(int[] arr) {
        Node temp = new Node(-1);
        Node curr = temp;
        for (int x : arr) {
            curr.next = new Node(x);
            curr = curr.next;
        }
        return temp.next;
    }

    
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" -> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Example 1: 1 -> 2 -> 3 -> 4 -> 5
        int[] arr1 = {1, 2, 3, 4, 5};
        Node head1 = createList(arr1);
        System.out.print("Original List 1: ");
        printList(head1);

        Node reversed1 = reverseList(head1);
        System.out.print("Reversed List 1: ");
        printList(reversed1);

        System.out.println();

    }
}
