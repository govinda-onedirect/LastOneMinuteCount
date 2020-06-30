import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DriverApplication {
    public static void main(String[] args){
        TaskExecutor te1= new TaskExecutor();
        Timer t=new Timer();

        //Can be modified the scheduler
        //Schedular running every 1 second
        t.scheduleAtFixedRate(te1, 0,1);

        //Call Threads Creation
        List<CallThread> callThreads = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            callThreads.add(new CallThread());
        }
        ExecutorService callService = Executors.newFixedThreadPool(100);

        //Count Thread Creation
        List<CountThread> countThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            countThreads.add(new CountThread());
        }
        ExecutorService countService = Executors.newFixedThreadPool(10);



        List<Future<Integer>> countFuture = null;
        List<Future<Boolean>> callFutures = null;
        try {
            callFutures = callService.invokeAll(callThreads);
            countFuture = countService.invokeAll(countThreads);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert callFutures != null;
        callFutures.parallelStream().forEach(booleanFuture -> {
            try {
                System.out.println(booleanFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });


        assert countFuture != null;
        countFuture.parallelStream().forEach(integerFuture -> {
            try {
                System.out.println(integerFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });    }
}
