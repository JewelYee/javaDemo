package com.yee.demo.javajdk.thread;

import java.util.concurrent.*;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020/10/19 5:07 PM
 */
public class Test {

        public static void main(String[] args) throws ExecutionException, InterruptedException {

            ExecutorService executor = Executors.newFixedThreadPool(3);

            CountDownLatch taskNum = new CountDownLatch(3);

            long start = System.currentTimeMillis();

            Future<String> f1 = executor.submit(() -> {


                System.out.println("simulate rpc query user info, userFacade.getUser()");

//                Thread.sleep(2000);
                return "userFacade.getUser()";

            });


            Future<String> f2 = executor.submit(() -> {
                System.out.println("simulate rpc query product info, productFacade.getProduct()");

//                Thread.sleep(3000);
                return " productFacade.getProduct()";

            });


            Future<String> f3 = executor.submit(() -> {
                System.out.println("simulate rpc query order info, orderFacade.getOrderDetail()");

//                Thread.sleep(1000);
                return "orderFacade.getOrderDetail()";

            });


            String r1 = f1.get();

            String r2 = f2.get();

            String r3 = f3.get();


            System.out.println(System.currentTimeMillis()-start);

            System.out.println(r1);
            System.out.println(r2);
            System.out.println(r3);


            executor.shutdown();


            /**
             * userInfo = userFacade.getUser()
             * ProductInfo= productFacade.getProduct()
             * orderInfo=orderFacade.getOrderDetail()
             * 传统方式是依次调用，时间各接口时间相加

             * 使用异步的方式，最终耗时是决定于耗时最大的接口时间

             */


        }


}
