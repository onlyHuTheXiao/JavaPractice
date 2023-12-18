package onlyhu.java.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁简单演示
 *
 */
public class LockDemo {


     //在纯读的操作中，不存在锁的使用场景
    // ReentrantLock：重入锁。  ReentrantReadWriteLock：重入读写锁
     // 分别维护了一个read和writer锁，适合于读多写少的场景
       static Lock reentrantLock = new ReentrantLock();

       public static int count = 0;


       public static void incr(){

           try {
               reentrantLock.lock();
               Thread.sleep(1);

               decr();

               count ++ ;
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }finally {
               //释放锁
               reentrantLock.unlock();
           }


       }

       public static void decr(){

            reentrantLock.lock();

            count -- ;

            reentrantLock.unlock();

       }


    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            new Thread(LockDemo::incr).start();


        }
        try {
            Thread.sleep(4000);

            System.out.printf(String.valueOf(count));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
