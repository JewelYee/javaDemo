package com.yee.demo.rocketmq.ordertest;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020/10/29 2:36 PM
 */
public class OrderedConsumer {

    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name");
        consumer.setNamesrvAddr("10.135.17.26:9876;10.135.17.27:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("TopicOrder", "TagA");

        consumer.registerMessageListener(new MessageListenerOrderly() {

            Random random = new Random(10);
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                       ConsumeOrderlyContext context) {
                context.setAutoCommit(true);

                for (MessageExt msg : msgs) {
                    System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + new String(msg.getBody()) + "%n");

                }

                try {
                    Thread.sleep(random.nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return ConsumeOrderlyStatus.SUCCESS;

            }
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
