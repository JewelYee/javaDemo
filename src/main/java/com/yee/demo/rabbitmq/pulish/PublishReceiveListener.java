package com.yee.demo.rabbitmq.pulish;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2022/3/5 6:03 PM
 */
@Component
public class PublishReceiveListener {

    @RabbitListener(queues = "queue_fanout1")
    public void receiveMsg1(String msg) {
        System.out.println("【发布订阅模型】队列1接收到消息：" + msg);
    }

    @RabbitListener(queues = "queue_fanout2")
    public void receiveMsg2(String msg) {
        System.out.println("【发布订阅模型】队列2接收到消息：" + msg);
    }



}
