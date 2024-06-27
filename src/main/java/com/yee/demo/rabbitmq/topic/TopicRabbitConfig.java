package com.yee.demo.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2022/3/5 6:41 PM
 */

//@Configuration
public class TopicRabbitConfig {
    //绑定键
    public final static String create = "topic.create";
    public final static String disburse = "topic.disburse";

    @Bean
    public Queue createQueue() {
        return new Queue(TopicRabbitConfig.create);
    }

    @Bean
    public Queue disburseQueue() {
        return new Queue(TopicRabbitConfig.disburse);
    }

    @Bean
    TopicExchange yeeTopicExchange() {
        return new TopicExchange("yeeTopicExchange");
    }

    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(createQueue()).to(yeeTopicExchange()).with(create);
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.*
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
//    @Bean
//    Binding bindingExchangeMessage2() {
//        return BindingBuilder.bind(disburseQueue()).to(yeeTopicExchange()).with("yee.topic.*");
//    }




}
