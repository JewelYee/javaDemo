package com.yee.demo.rocketmq.ordertest;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020/10/29 2:35 PM
 */
public class OrderedProducer {

    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("example_group_name");
        producer.setNamesrvAddr("10.135.17.26:9876;10.135.17.27:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 10; i++) {
            int orderId = 0;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicOrder","TagA", "KEY" + i,
                    ("Hello RocketMQ " + i).getBytes());
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);

            System.out.printf("%s%n", sendResult);
        }
        //server shutdown
        producer.shutdown();
    }
}
