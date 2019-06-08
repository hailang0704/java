
public class LockDead2
{
    public  Object obj1 = new Object();
    public  Object obj2 = new Object();
    public Runnable task1 = () -> {
        synchronized (obj1) {
            System.out.println("obj1");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            synchronized (obj2) {     //此同步代码块在另一同步代码块里
                System.out.println("obj11");
            }

        }
    };

    public Runnable task2 = () -> {
        synchronized (obj2) {
            System.out.println("obj2");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//            }
            synchronized (obj1) {     //此同步代码块在另一同步代码块里
                System.out.println("obj21");
            }
        }
    };

    public static void main(String args[]){
        LockDead2 ld = new LockDead2();
        Thread th1 = new Thread(ld.task1);
        Thread th2 = new Thread(ld.task2);
        th1.start();
        th2.start();
    }
}  