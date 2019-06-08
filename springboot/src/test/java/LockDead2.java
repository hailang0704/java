
public class LockDead2
{
    public  Object obj1 = new Object();
    public  Object obj2 = new Object();
    public Runnable task1 = () -> {
        synchronized (a) {
            a.get();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            synchronized (b) {     //此同步代码块在另一同步代码块里
                a.say();
            }

        }
    }

    public Runnable task2 = () -> {
        synchronized (b) {
            b.get();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            synchronized (a) {     //此同步代码块在另一同步代码块里

                b.say();
            }
        }
    }

    public static void main(String args[]){
        LockDead2 ld = new LockDead2();
        Thread th1 = new Thread(ld.task1);
        Thread th2 = new Thread(ld.task2);
        th1.start();
        th2.start();
    }
}  