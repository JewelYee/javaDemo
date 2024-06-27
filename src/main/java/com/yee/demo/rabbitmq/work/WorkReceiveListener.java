package com.yee.demo.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2022/3/5 4:56 PM
 */
@Component
public class WorkReceiveListener {

    @RabbitListener(queues = "queue_work")
    public void receiveMessage(String msg) {
        // 只包含发送的消息
        System.out.println("1接收到消息：" + msg);
        // channel 通道信息
        // message 附加的参数信息
    }

}
