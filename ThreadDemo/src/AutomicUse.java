import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liudi on 2018/1/15.
 */
public class AutomicUse{
    private static AtomicInteger count = new AtomicInteger(0);

    public synchronized int multiAdd() throws InterruptedException {
        Thread.sleep(100);
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4);
        return count.get();
    }

    public static void main(String[] args) {
        AutomicUse automicUse = new AutomicUse();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i=0;i<100;i++){
            threads.add(new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        System.out.println(automicUse.multiAdd());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        for(int i=0;i<100;i++){
            threads.get(i).start();
        }



    }
}
