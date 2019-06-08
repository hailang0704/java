import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
public class AtomicCounter {

    private AtomicInteger value = new AtomicInteger();

    public int getValue() {
        return value.get();
    }

    public int increase() {
        return value.incrementAndGet();// 内部使用死循环for(;;)调用compareAndSet(current, next)
        //		return value.getAndIncrement();
    }

    public int increase(int i) {
        return value.addAndGet(i);// 内部使用死循环for(;;)调用compareAndSet(current, next)
        //		return value.getAndAdd(i);
    }

    public int decrease() {
        return value.decrementAndGet();// 内部使用死循环for(;;)调用compareAndSet(current, next)
        //		return value.getAndDecrement();
    }

    public int decrease(int i) {
        return value.addAndGet(-i);// 内部使用死循环for(;;)调用compareAndSet(current, next)
        //		return value.addAndGet(-i);
    }

    public static void main(String[] args) {
        final AtomicCounter counter = new AtomicCounter();
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
