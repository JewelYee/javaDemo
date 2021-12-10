package com.yee.demo.javajdk.thread;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/10 11:56 AM
 */
public class RunnableTest implements Runnable {

    private String taskName;

    public RunnableTest(final String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Inside "+taskName);
        throw new RuntimeException("RuntimeException from inside " + taskName);
    }

}
