package classwork;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class solve {

    // HashMap<String, onHold> map= new HashMap<>();

    

    // public void shouldPrintMessage(int timestamp, String message) {

    //     if (!map.containsKey(message)) {
    //         map.put(message, timestamp);
    //         System.out.println(message);
    //         return;
    //     }

    //     int lastTime = map.get(message);

    //     if (timestamp - lastTime >= 10) {
    //         map.put(message, timestamp);
    //         System.out.println(message);
    //     }
    // }

        /* 
        
    static class RobotMessageTracker {
    
    class MessageNode {
        long timeStamp;
        String message;
        String status;

        MessageNode(long timeStamp, String message) {
            this.timeStamp = timeStamp;
            this.message = message;
            this.status = "ON_HOLD";
        }
    }

    public Map<String, MessageNode> messageTracker;
    public Queue<MessageNode> queue;

    RobotMessageTracker() {
        messageTracker = new HashMap<>();
        queue = new LinkedList<>();
    }

    

    
    public void shouldPrintMessage(long timeStamp, String message ) {
        
        MessageNode newMessage  = new MessageNode(timeStamp, message);
        MessageNode prevMessage = messageTracker.get(message);

        

        // Check Whether this message was in the past 

        if (prevMessage != null && (newMessage.timeStamp - prevMessage.timeStamp) < 10) {
            prevMessage.status = "CANCELLED";
            newMessage.status = "CANCELLED";
        }

        // Hold this message for future refernce
        messageTracker.put(message, newMessage);
        queue.offer(newMessage);
        
        // print All the valid Message, Time window has breached of 10 seconds.

        while(!queue.isEmpty() && (timeStamp - queue.peek().timeStamp) >= 10) {
            MessageNode messageFromQueue = queue.poll();
            if (messageFromQueue.status.equals("ON_HOLD")) {
                System.out.println(messageFromQueue.timeStamp + " " + messageFromQueue.message);
            }
        }

    }
}

    
    public static void main(String[] args) {
        RobotMessageTracker logger= new RobotMessageTracker();
        logger.shouldPrintMessage(1, "foo");
        logger.shouldPrintMessage(2, "foo");
        logger.shouldPrintMessage(15, "foo");
        logger.shouldPrintMessage(15, "foo");
        logger.shouldPrintMessage(35, "foo");
        logger.shouldPrintMessage(55, "foo");


    }

    */

    // 20/7/26

    public static TreeMap<Integer, Integer> helper(int[] arr) {
        TreeMap<Integer, Integer> map= new TreeMap<>();
        for(int val : arr) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return map;
    }
    public static void main(String[] args) {
        int arr[]= {0, 1, 2,3,4, 7, 8};

        TreeMap<Integer, Integer> map= helper(arr);

        int maxCount= Integer.MIN_VALUE;
        Integer prev= null;
        int count= 1;
        for(int val : map.keySet()) {

            if(prev != null && val == prev + 1) {
                count++;
            }
            else {
                count= 1;
            }
            maxCount= Math.max(count, maxCount);
            prev= val;

        }

        System.out.println("Max count= " + maxCount);

    }

    /*
        mport java.util.*;
class Main {
    static int LongestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int current = num;
                int count = 1;
                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }
                longest = Math.max(longest, count);
            }
        }
        return longest;
    }
    public static void main(String[] args) {
       
        int[] nums = {100, 4, 200, 1, 3, 2,-1,-2,-3,-100,0};
        System.out.print(LongestConsecutive(nums));
    }
}
    
    
    */


        

}
