import java.util.Collections;
import java.util.PriorityQueue;

public class runningMedian {
    class MedianFinder {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianFinder() {
            left = new PriorityQueue<>(Collections.reverseOrder()); // max heap
            right = new PriorityQueue<>(); // min heap
        }

        public void addNum(int num) {
            if (right.size() > 0 && num > right.peek()) {
                right.add(num);
            } else {
                left.add(num);
            }

            // balancing
            if (left.size() - right.size() == 2) {
                right.add(left.remove());
            } else if (right.size() - left.size() == 2) {
                left.add(right.remove());
            }
        }

        public double findMedian() {
            if (left.size() == right.size()) {
                int medLeft = left.peek();
                int medRight = right.peek();

                double median = (double) (medLeft + medRight) / 2;
                return median;
            } else if (left.size() > right.size()) {
                return left.peek();
            } else {
                return right.peek();
            }
        }
    }
}
