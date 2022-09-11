public class ZKDistributedLockTest {


    public static void doSomething(){
        System.out.println("线程【" + Thread.currentThread().getName() + "】正在运行...");
    }

    public static void main(String[] args) {
        String host = "localhost:2181";
        String rootLock = "/locks";
        String lockName = "lock";
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                ZKDistributedFairLock zkDistributedFairLock = new ZKDistributedFairLock(host,rootLock,lockName);
                if(zkDistributedFairLock.lock()){
                    doSomething();
                    try{
                        Thread.sleep(1000);
                        zkDistributedFairLock.unLock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        for(int i = 0; i < 5; i++){

            Thread t = new Thread(runnable);
            t.start();
        }



    }
}
