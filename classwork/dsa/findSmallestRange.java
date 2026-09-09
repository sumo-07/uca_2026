import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class findSmallestRange {
    static class Pair {
        int value;
        int listIndex;
        int dataIndex;

        Pair(int value, int listIndex, int dataIndex) {
            this.value= value;
            this.listIndex= listIndex;
            this.dataIndex= dataIndex;
        }
    }

    static void find(List<List<Integer>> lists) {

        PriorityQueue<Pair> pq= new PriorityQueue<>();

        for(int i=0; i < lists.size(); i++) {
            Pair np= new Pair(lists.get(i).get(0), i, 0);
            pq.add(np);
        }

        while (pq.size() > 0) {
            
        }

    }    

    public static void main(String[] args) {
        // int[][] nums= {{4, 10, 15, 24, 26}, {0, 9, 12,20}, {5, 18, 22, 30}};
        // int[][] nums2= {{1,2,3}, {1,2,3}, {1,2,3}};

        // find(nums2);
        // find(nums);


    }
}
