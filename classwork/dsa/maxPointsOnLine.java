import java.util.HashMap;

public class maxPointsOnLine {
    class Solution {
        public int maxPoints(int[][] points) {
            int n = points.length;
            if (n <= 2) {
                return n;
            }

            int answer = 0;

            for (int i = 0; i < n; i++) {
                HashMap<String, Integer> map = new HashMap<>();
                int maxSlope = 0; // count the no.of maxSlopes points
                int duplicate = 0;
                for (int j = i + 1; j < n; j++) {
                    int dx = points[j][0] - points[i][0];
                    int dy = points[j][1] - points[i][1];

                    // same point
                    if (dy == 0 && dx == 0) {
                        duplicate++;
                        continue;
                    }

                    int gcd = gcd(dx, dy); // to get the smallest fraction

                    dx /= gcd;
                    dy /= gcd;

                    // handling negative
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }

                    /// handling the above zero or bottom zero
                    // vertical line
                    if (dx == 0) {
                        dy = 1;
                    }

                    // Horizontal line
                    if (dy == 0) {
                        dx = 1;
                    }

                    String slope = dy + "/" + dx;

                    int count = map.getOrDefault(slope, 0) + 1;
                    map.put(slope, count);

                    maxSlope = Math.max(maxSlope, count);
                }

                answer = Math.max(answer, maxSlope + duplicate + 1); // (1, 1) --> two times aaya toh uska dupplicate
                                                                     // bhi toh straight line pe hee hoga, and plus 1 we
                                                                     // do because we have not taken the starting point
            }

            return answer;
        }

        public int gcd(int a, int b) {
            a = Math.abs(a);
            b = Math.abs(b);

            while (b != 0) {
                int temp = a % b;
                a = b;
                b = temp;
            }

            return a;
        }
    }
}
