import java.util.concurrent.Callable;

public class CallThread implements Callable<Boolean> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Boolean call() throws Exception {
        return QueueManager.call();
    }
}
