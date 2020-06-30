import java.util.TimerTask;

public class TaskExecutor extends TimerTask {

    @Override
    public void run() {
        QueueManager.poll();
    }

}
