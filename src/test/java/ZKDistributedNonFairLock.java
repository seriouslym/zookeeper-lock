import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZKDistributedNonFairLock implements Watcher {

    private ZooKeeper zooKeeper;
    private String rootLockPath;
    private String lockName; // 在根节点下生成唯一的临时节点
    private int sessionTimeout = 3000;
    private CountDownLatch countDownLatch;
    public ZKDistributedNonFairLock(String host, String rootLock, String lockName) {
        this.rootLockPath = rootLock;
        this.lockName = lockName;
        try{
            zooKeeper = new ZooKeeper(host,sessionTimeout,this);
            Stat stat =  zooKeeper.exists(rootLockPath,false);
            if(stat == null){
                zooKeeper.create(rootLockPath,new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public boolean lock(){
       if(tryLock()){
           return true;
       }else{
           return waitLock();
       }

    }
    public boolean tryLock(){
        String lockPath = rootLockPath + "/" + lockName;
        try {
            zooKeeper.create(lockPath,new byte[0],ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            System.out.println("线程【" + Thread.currentThread().getName() + "】加锁成功") ;
            return true;
        } catch (InterruptedException | KeeperException e ) {
            System.out.println("线程【" + Thread.currentThread().getName() + "】获取锁失败 等待锁");
        }
        return false;

    }

    public boolean waitLock(){
        boolean isLock = false;
        String lockPath = rootLockPath + '/' + lockName;
        try {
            Stat stat = zooKeeper.exists(lockPath,true);
            if(stat != null){
                countDownLatch = new CountDownLatch(1);
                countDownLatch.await();
            }
            isLock = lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return isLock;

    }

    public void unlock(){
        String lockPath = rootLockPath + '/' + lockName;
        try{
            Stat stat = zooKeeper.exists(lockPath,true);
            if(stat != null){
                System.out.println("线程【" + Thread.currentThread().getName() + "】释放锁 ");
                zooKeeper.delete(lockPath,-1);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(countDownLatch != null && watchedEvent.getType() == Event.EventType.NodeDeleted){
            countDownLatch.countDown();
        }
    }
}
