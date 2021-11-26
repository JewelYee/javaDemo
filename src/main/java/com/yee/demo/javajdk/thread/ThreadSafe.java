package com.yee.demo.javajdk.thread;

/**
 * @Desciption: 使用 interrupt()方法来中断线程有两种情况:
 *      1.线程处于阻塞状态:如使用了 sleep,同步锁的 wait,socket 中的 receiver,accept 等方法时，
 * 会使线程处于阻塞状态。当调用线程的 interrupt()方法时，会抛出 InterruptException 异常。
 * 阻塞中的那个方法抛出这个异常，通过代码捕获该异常，然后 break 跳出循环状态，从而让
 * 我们有机会结束这个线程的执行。通常很多人认为只要调用 interrupt 方法线程就会结束，实
 * 际上是错的， 一定要先捕获 InterruptedException 异常之后通过 break 来跳出循环，才能正
 * 常结束 run 方法。
 *
 *      2.线程未处于阻塞状态:使用 isInterrupted()判断线程的中断标志来退出循环。当使用
 * interrupt()方法时，中断标志就会置 true，和使用自定义的标志来控制循环是一样的道理。
 *
 * @Auther: yee
 * @Date:2021/11/26 11:06 AM
 */
public class ThreadSafe extends Thread {

    @Override
    public void run() {
        //非阻塞过程中通过判断中断标志来退出
        while (!isInterrupted()) {
            try {
                //阻塞过程捕获中断异常来退出
                Thread.sleep(5 * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
                //捕获到异常之后，执行 break 跳出循环
                break;
            }
        }
    }
}