import java.time.LocalDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueManager {
    private static final int seconds =5;
    private static final Queue<LocalDateTime> queue = new ConcurrentLinkedQueue<>();


    public static boolean call(){
        return queue.add(LocalDateTime.now());
    }

    public static int count(){
        return queue.size();
    }

    public static void poll(){
        System.out.println("-----Polling----");
        LocalDateTime localDateTime = LocalDateTime.now().minusSeconds(seconds);
        while (queue.peek().isBefore(localDateTime)){
            System.out.println(queue.poll());
        }
    }
}
