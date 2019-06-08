import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncronizedCounter {

    public int value = 1;


    public synchronized int increase() {
        return value++;
    }



    public static void main(String[] args) {
        final SyncronizedCounter counter = new SyncronizedCounter();
        ExecutorService service = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 100; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" "+counter.increase());
                }
            });
        }
        service.shutdown();
    }
}
