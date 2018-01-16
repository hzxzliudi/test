/**
 * Created by liudi on 2018/1/15.
 */
public class RunThread extends Thread{
     volatile private  boolean isRunning = true;

    @Override
    public void run() {
        System.out.println("线程开始");
        while(isRunning == true){

        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程结束");
    }
    public void setRunning(Boolean flag){
        isRunning = flag;
    }

    public static void main(String[] args) throws InterruptedException{
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(3000);
        runThread.setRunning(false);
       System.out.println("设置了false");
        Thread.sleep(1000);
        System.out.println(runThread.isRunning);
    }
}
