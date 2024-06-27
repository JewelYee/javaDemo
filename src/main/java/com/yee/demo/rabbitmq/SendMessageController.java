package com.yee.demo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2022/3/5 4:50 PM
 */
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法



    @RequestMapping("/sendWork")
    public Object sendWork() {
        rabbitTemplate.convertAndSend("queue_work", "测试work模型:" );
        return "发送成功...";
    }

    @RequestMapping("/sendPublish")
    public String sendPublish() {
        rabbitTemplate.convertAndSend("exchange_fanout", "", "测试发布订阅模型：" );
        return "发送成功...";
    }

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", "map");
        return "ok";
    }

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        String messageData = "message: create ";

        rabbitTemplate.convertAndSend("topicExchange", "topic.create", messageData);
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageData = "message: disburse ";
        rabbitTemplate.convertAndSend("topicExchange", "topic.disburse", messageData);
        return "ok";
    }

    @GetMapping("/sendTopicMessage3")
    public String sendTopicMessage3() {
        String messageData = "message: disburse ";
        rabbitTemplate.convertAndSend("exchange.yee", "yee.topic.*", messageData);
        return "ok";
    }

}
