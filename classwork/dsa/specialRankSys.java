import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class specialRankSys {
    public class Pair {
        char team;
        ArrayList<Integer> pos;

        Pair(char team) {
            this.team= team;
            pos= new ArrayList<>();
        }
    }

    
    public static String rankTeams(String[] votes) {
        PriorityQueue<Pair> pq= new PriorityQueue<>();
        
        Pair np = new Pair(votes[0].charAt(0));
        for(String vote : votes) {
            for(int i=0; i<vote.length(); i++) {
                pq.add(np);
            }
        }
    }

    public static void main(String[] args) {
        String[] votes= {"ABC", "ACB", "ABC", "ACB", "ACB"};
        System.out.println(rankTeams(votes));
    }   
}
