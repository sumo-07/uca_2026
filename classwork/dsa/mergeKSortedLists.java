import java.util.ArrayList;
import java.util.PriorityQueue;

public class mergeKSortedLists {

    public static class ListNode implements Comparable<ListNode> {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int val) {
            this.val= val;
        }

        ListNode(int val, ListNode next) {
            this.val= val;
            this.next= next;
        }

        public int compareTo(ListNode o) {
            return this.val - o.val;
        }


    }

    public static ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> pq= new PriorityQueue<>();
        ListNode temp= new ListNode(-1);
        ListNode curr= temp;

        for(ListNode head : lists) {
            
            pq.add(head);
            
        }
        

        while(pq.size() > 0) {
            ListNode top= pq.remove();
            curr.next= new ListNode(top.val);
            curr= curr.next;

            if(top.next != null) {
                pq.add(top.next);
            }
        }

        return temp.next;


    }

    public static ListNode createList(int[] arr) {
        ListNode temp= new ListNode(-1);
        ListNode curr= temp;

        for(int x : arr) {
            curr.next= new ListNode(x);
            curr= curr.next;
        }
        return temp.next;
    }

    public static void printList(ListNode res) {
        ListNode curr= res;

        while(curr != null) {
            System.out.print(curr.val + " ");
            curr= curr.next;
        }
    }

    public static void main(String[] args) {
        int[] a1= {1,4,5};
        int[] a2= {1,3,4};
        int[] a3= {2, 6};

        ListNode[] lists= new ListNode[3];

        lists[0]= createList(a1);
        lists[1]= createList(a2);
        lists[2]= createList(a3);

        ListNode res= mergeKLists(lists);
        printList(res);
        
        
        
        
    }
}
