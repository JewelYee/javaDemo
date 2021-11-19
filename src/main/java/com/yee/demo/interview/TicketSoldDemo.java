package com.yee.demo.interview;

/**
 * 多线程售票
 * Date:2021/11/3 9:10 下午
 */
public class TicketSoldDemo {

    private volatile int num = 100;

    public static void main(String[] args) {
        TicketSoldDemo ticket = new TicketSoldDemo();

        Thread t1 = new Thread(new Task(3000, ticket),"窗口1");
        Thread t2 = new Thread(new Task(5000, ticket),"窗口2");
        Thread t3 = new Thread(new Task(7000, ticket),"窗口3");

        t1.start();
        t2.start();
        t3.start();
    }

    static class Task implements Runnable {
        private long useTime;
        private TicketSoldDemo ticket;

        public Task(long useTime, TicketSoldDemo ticket) {
            this.useTime = useTime;
            this.ticket = ticket;
        }
        @Override
        public void run() {
            while (ticket.num > 0) {
                try {
                    --ticket.num;
                    System.out.println(Thread.currentThread().getName()+" 售出一张票");
                    Thread.sleep(useTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
