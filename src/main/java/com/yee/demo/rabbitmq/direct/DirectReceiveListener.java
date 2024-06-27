package com.yee.demo.rabbitmq.direct;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2022/3/5 4:56 PM
 */
@Component
public class DirectReceiveListener {

//    @RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
//    public void process(String msg) {
//        System.out.println("DirectReceiver消费者收到消息  : " + JSON.toJSONString(msg));
//    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("yee.topic.hahah"),
            exchange = @Exchange(name = "exchange.yee", type = ExchangeTypes.DIRECT),
            key = "yee.topic.*"))
    public void onMsg(String msg) {
        System.out.println("yee.topic.hahah  : " + JSON.toJSONString(msg));
    }

//    @RabbitListener(queues = "TestDirectQueue")
//    public void onMsg(String msg) {
//        System.out.println("yee.topic.hahah  : " + JSON.toJSONString(msg));
//    }

//    @RabbitListener(queues = "yee.topic.eheh")
//    public void onMsg1(String msg) {
//        System.out.println("yee.topic.eheh  : " + JSON.toJSONString(msg));
//    }

}
