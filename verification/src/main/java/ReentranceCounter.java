import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranceCounter {

    public int value = 1;

    public int increase() {
        return value++;
    }



    public static void main(String[] args) {
        final ReentrantLock reLock = new ReentrantLock();
        final ReentranceCounter counter = new ReentranceCounter();

        ExecutorService service = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 100; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    reLock.lock();
                    System.out.println(Thread.currentThread().getName()+" "+counter.increase());
                    reLock.unlock();
                }
            });
        }
        service.shutdown();
    }
}
