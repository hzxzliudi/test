import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudi on 2018/1/16.
 */
public class WaitNotify {
    private volatile static List list = new ArrayList<>();

    public void add() {
        list.add("jiajia");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final WaitNotify countDownlock = new WaitNotify();
        final Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            countDownlock.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加一个元素..");
                            Thread.sleep(500);
                            if (countDownlock.size() == 5) {
                                System.out.println("发出通知");
                                lock.notify();
                            }

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    if (countDownlock.size() != 5) {
                        try {
                            System.out.println("lock.wait");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程" + Thread.currentThread().getName() + "收到线程停止通知");
                    throw new RuntimeException();
                }
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
