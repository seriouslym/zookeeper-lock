# zookeeper-lock
## 利用zookeeper模拟分布式锁  
### 1、公平锁（按照线程创建的循序获取锁）
![image](https://user-images.githubusercontent.com/61036359/189525926-a8741c03-2f92-4ef0-9058-53bcd7e6977a.png)  

每个线程创建一个临时有序节点，并判断当前节点是否是最小的，如果是则获锁成功，如果不是监听前一个节点的同时，利用CountDownLatch阻塞当前线程。  
### 2、非公平锁
![image](https://user-images.githubusercontent.com/61036359/189526069-78ea5e0a-6221-49f2-98ba-c697beb6f7fc.png)

每个线程都去争抢同一个临时节点（只有一个线程竞争成功），失败的线程对节点添加监听事件并进行阻塞，当锁被释放后，通知所有监听该节点的线程去竞争。  
