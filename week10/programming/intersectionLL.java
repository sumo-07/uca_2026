public class intersectionLL {
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static Node findIntersection(Node head1, Node head2) {
        Node dummy = new Node(-1);
        Node curr = dummy;

        Node p1 = head1;
        Node p2 = head2;

        
        while (p1 != null && p2 != null) {
            if (p1.val == p2.val) {
                
                curr.next = new Node(p1.val);
                curr = curr.next;
                p1 = p1.next;
                p2 = p2.next;
            } else if (p1.val < p2.val) {
                p1 = p1.next;
            } else {
                p2 = p2.next;
            }
        }

        return dummy.next;
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
        // Example 1
        int[] arr1 = {1, 2, 3, 4, 6};
        int[] arr2 = {2, 4, 6, 8};

        Node head1 = createList(arr1);
        Node head2 = createList(arr2);

        System.out.print("head1: ");
        printList(head1);
        System.out.print("head2: ");
        printList(head2);

        Node result1 = findIntersection(head1, head2);
        System.out.print("Intersection: ");
        printList(result1);

        System.out.println();

        // Example 2 (with duplicates)
        int[] arr3 = {1, 2, 2, 3, 4};
        int[] arr4 = {2, 2, 2, 4};

        Node head3 = createList(arr3);
        Node head4 = createList(arr4);

        System.out.print("head1: ");
        printList(head3);
        System.out.print("head2: ");
        printList(head4);

        Node result2 = findIntersection(head3, head4);
        System.out.print("Intersection: ");
        printList(result2);
    }
}
