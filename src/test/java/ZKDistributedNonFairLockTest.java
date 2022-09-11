public class ZKDistributedNonFairLockTest {


    public static void doSomething(){
        System.out.println("线程【" + Thread.currentThread().getName() + "】正在运行...");
    }

    public static void main(String[] args) {
        String host = "localhost:2181";
        String rootLock = "/lock";
        String lockName = "lock";

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ZKDistributedNonFairLock zkDistributedNonFairLock = new ZKDistributedNonFairLock(host,rootLock,lockName);

                if(zkDistributedNonFairLock.lock()){
                    doSomething();
                    try{
                        Thread.sleep(3000);
                        zkDistributedNonFairLock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }

}
