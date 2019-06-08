import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileCounter {

    public volatile int value = 1;


    public int increase() {
        return value++;
    }



    public static void main(String[] args) {
        final VolatileCounter counter = new VolatileCounter();
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
