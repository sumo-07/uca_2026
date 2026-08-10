import java.util.Collections;
import java.util.PriorityQueue;

public class clinicVillage {

    public static class Pair implements Comparable<Pair> {
        int population;
        int clinics;

        Pair(int population, int clinics) {
            this.population= population;
            this.clinics= clinics;
        }

        public int compareTo(Pair o) {
            double thisLoad= (double) this.population / this.clinics;
            double otherLoad= (double) o.population / o.clinics;
            if(thisLoad < otherLoad) {
                return -1;
            }
            else if(thisLoad > otherLoad) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    public static double maxLoad(int[] arr, int k) { // arr= population array, k= clinics
        PriorityQueue<Pair> pq= new PriorityQueue<>(Collections.reverseOrder());
        int n= arr.length;
        for(int x : arr) {
            pq.add(new Pair(x, 1));
        }

        int remC= k - n;

        while(remC > 0) {
            Pair maxLoadVillage= pq.remove();

            maxLoadVillage.clinics++;
            remC--;

            pq.add(maxLoadVillage);
        }

        Pair max= pq.remove();
        double maxLoad= (double) max.population / max.clinics;
        return Math.round(maxLoad * 100.0) / 100.0;

    }

    public static void main(String[] args) {
        int[] population = {200, 20, 50};
        int k = 5;

        System.out.println(maxLoad(population, k));
    }
}
