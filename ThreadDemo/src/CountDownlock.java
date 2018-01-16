import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liudi on 2018/1/16.
 */
public class CountDownlock {
    private volatile static List list = new ArrayList<>();

    public void add() {
        list.add("jiajia");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final CountDownlock countDownlock = new CountDownlock();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        countDownlock.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加一个元素..");
                        Thread.sleep(500);
                        if(countDownlock.size()==5){
                            System.out.println("发出通知");
                            countDownLatch.countDown();
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(countDownlock.size()!=5){
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前线程"+Thread.currentThread().getName()+"收到线程停止通知");
                throw new RuntimeException();
            }
        },"t2");
        t2.start();
        t1.start();
    }
}
