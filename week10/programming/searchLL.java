public class searchLL {
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static String isPresent(Node list1, Node list2) {
        if (list1 == null) return "Yes";
        if (list2 == null) return "No";

        Node ptr2 = list2;

        while (ptr2 != null) {
            Node p1 = list1;
            Node p2 = ptr2;

            while (p1 != null && p2 != null && p1.val == p2.val) {
                p1 = p1.next;
                p2 = p2.next;
            }

            if (p1 == null) {
                return "Yes";
            }

            ptr2 = ptr2.next;
        }

        return "No";
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
        Node list1_ex1 = createList(new int[]{10, 20});
        Node list2_ex1 = createList(new int[]{5, 10, 20});

        System.out.print("list1: ");
        printList(list1_ex1);
        System.out.print("list2: ");
        printList(list2_ex1);
        System.out.println("Output: " + isPresent(list1_ex1, list2_ex1));

        System.out.println();

        // Example 2
        Node list1_ex2 = createList(new int[]{1, 2});
        Node list2_ex2 = createList(new int[]{1, 2, 1, 2, 3, 4});

        System.out.print("list1: ");
        printList(list1_ex2);
        System.out.print("list2: ");
        printList(list2_ex2);
        System.out.println("Output: " + isPresent(list1_ex2, list2_ex2));

        System.out.println();

        // Example 3
        Node list1_ex3 = createList(new int[]{1, 2, 3, 4});
        Node list2_ex3 = createList(new int[]{1, 2, 2, 1, 2, 3});

        System.out.print("list1: ");
        printList(list1_ex3);
        System.out.print("list2: ");
        printList(list2_ex3);
        System.out.println("Output: " + isPresent(list1_ex3, list2_ex3));
    }
}
