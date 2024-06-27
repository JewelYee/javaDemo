package com.yee.demo.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2022/3/5 6:48 PM
 */

//@Component
public class TopicReceiverListener {

    @RabbitListener(queues = "topic.disburse")
    public void process(String testMessage) {
        System.out.println("topic.disburse  : " + testMessage);
    }

//    @RabbitListener(queues = "yee.topic.disburse")
//    public void process1(String testMessage) {
//        System.out.println("yee.topic.disburse  : " + testMessage);
//    }

}
