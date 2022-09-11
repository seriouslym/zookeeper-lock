import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZKDistributedFairLock implements Watcher {
    private ZooKeeper zooKeeper;
    private String rootLock;   // 根节点
    private String lockName;   // 生成的节点的前缀
    private String currentLock; //  当前锁节点
    private String waitLock;   // 等待的锁（前一个锁
    private CountDownLatch countDownLatch;  // 竞争锁失败 线程阻塞
    private int sessionTimeout = 30000;
//    连接 并检查根节点是否存在 不存在生成一个根节点
    public ZKDistributedFairLock(String host, String rootLock, String lockName){

        this.rootLock = rootLock;
        this.lockName = lockName;

        try {
            zooKeeper = new ZooKeeper(host,sessionTimeout,this);
            Stat stat = zooKeeper.exists(rootLock, false);
            if(stat == null){
                zooKeeper.create(rootLock, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
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
            System.out.println("线程【" + Thread.currentThread().getName() + "】加锁（" + this.currentLock + ") 成功") ;
            return true;
        }else{
            return waitOtherLock(waitLock, sessionTimeout);
        }



    }


    public boolean tryLock(){
//        先创建一个临时有序节点，然后获取根节点所有的孩子节点 判断当前节点是否是最小的 如果是 获取锁成功 如果不是 则竞争失败 进行阻塞
        try {
            this.currentLock = zooKeeper.create(rootLock + "/" + lockName, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("线程【" + Thread.currentThread().getName()
                    + "】创建锁节点（" + this.currentLock + "）成功，开始竞争...");

            List<String> children = zooKeeper.getChildren(rootLock, false);
            Collections.sort(children);
//            放心大胆取list的第一个元素，因为走到这里，最起码有一个线程成功创建了节点
            String realLockPath = this.rootLock + "/" + children.get(0);
            if(realLockPath.equals(currentLock)){
//                加锁成功
                return true;
            }
//            加锁失败  找到当前节点的前一个节点
            String currentLockNodeName = this.currentLock.substring(currentLock.lastIndexOf("/") + 1);
            int idx = Collections.binarySearch(children, currentLockNodeName);
            this.waitLock = children.get(idx - 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean waitOtherLock(String waitLock, int sessionTimeout){
        boolean isLock = false;
        try {
            String waitLockPath = rootLock + "/" + waitLock;
            Stat stat = zooKeeper.exists(waitLockPath,true);
            if(null != stat){
                System.out.println("线程【" + Thread.currentThread().getName()
                        + "】锁（" + this.currentLock + "）加锁失败，等待锁（" + waitLockPath + "）释放...");
                this.countDownLatch = new CountDownLatch(1);
                isLock = this.countDownLatch.await(sessionTimeout, TimeUnit.MILLISECONDS);
                this.countDownLatch = null;
                if(isLock){
//                    在前一个节点没有delete之前 此线程一直在阻塞
//                    tryLock();
                    System.out.println("线程【" + Thread.currentThread().getName() + "】锁（"
                            + this.currentLock + "）加锁成功，锁（" + waitLockPath + "）已经释放");
                }else{
                    System.out.println("线程【" + Thread.currentThread().getName() + "】锁（"
                            + this.currentLock + "）加锁失败...");
                }

            }else{
                isLock = true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return isLock;

    }




    public void unLock() throws InterruptedException {

        try {
            Stat stat = zooKeeper.exists(currentLock,false);
            if(null != stat){
                System.out.println("线程【" + Thread.currentThread().getName() + "】释放锁 " + this.currentLock);
                zooKeeper.delete(currentLock,-1);
                this.currentLock = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }finally {
            zooKeeper.close();
        }


    }



    @Override
    public void process(WatchedEvent watchedEvent) {
//        System.out.println(watchedEvent.getPath());
        if(null != countDownLatch && watchedEvent.getType() == Event.EventType.NodeDeleted){
            countDownLatch.countDown();
        }
    }
}
