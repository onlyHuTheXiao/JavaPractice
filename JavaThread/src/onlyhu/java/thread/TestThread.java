package onlyhu.java.thread;

public class TestThread {


    /**
     * 现在有 T1、T2、T3 三个线程，你怎样保证 T2 在 T1 执行完后执行，T3 在 T2 执行完后执行 ?
     * @param args
     */

    public static void main(String[] args) {


        ThreadJoinTest t1 = new ThreadJoinTest("线程T1");
        ThreadJoinTest t2 = new ThreadJoinTest("线程T2");
        ThreadJoinTest t3 = new ThreadJoinTest("线程T3");



        t1.start();
//        t2.start();

        try {
            /**
             * join方法可以传递参数，join(10)表示main线程会等待t1线程10毫秒，10毫秒过去后，
             * main线程和t1线程之间执行顺序由串行执行变为普通的并行执行
             */

//            t1.join(5);

            // 当使用join()无参方法时：可以看到t2会等到t1执行完毕才开始执行。
            t1.join();
            t2.start();

            //问：join与start调用顺序问题
            //答：join方法必须在线程start方法调用之后调用才有意义。这个也很容易理解：如果一个线程都没有start，那它也就无法同步了。因为执行完start方法才会创建线程。

            //问：join方法实现原理
            //答：join方法是通过调用线程的wait方法来达到同步的目的的。例如A线程中调用了B线程的join方法，
            // 则相当于在A线程中调用了B线程的wait方法，当B线程执行完（或者到达等待时间），B线程会自动调用自身的notifyAll方法唤醒A线程，从而达到同步的目的。

            //问：源码分析join方法
            //isAlive()判断线程是否还活着，即线程是否还未终止。
            //答：由下面的join方法源码可以看到：
            //1、如果join方法传参为0的话，则会调用isAlive()方法，一直检测线程是否存活（执行完毕）,如果存活就调用wait方法，一直阻塞。
            //2、如果参数为负数，则直接报错：“timeout value is negative”
            //3、如果参数大于0，则while里面一直判断线程是否存活，存活的话就一直判断当前线程执行的时间并与计算还需要等待多久时间，
            // 最后如果等待时间小于等于0就跳出循环，否则就继续wait



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}
