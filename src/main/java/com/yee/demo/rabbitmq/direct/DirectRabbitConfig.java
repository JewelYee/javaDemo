package com.yee.demo.rabbitmq.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2022/3/5 4:31 PM
 */
@Configuration
public class DirectRabbitConfig {

    public static String routingKey = "yee.topic.*";
    //队列 起名：TestDirectQueue
    @Bean
    public Queue TestDirectQueue() {
        return new Queue("TestDirectQueue");
    }
    @Bean
    public Queue TestDirectQueue1() {
        return new Queue("TestDirectQueue1");
    }


    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("TestDirectExchange");
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }
    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect1() {
        return BindingBuilder.bind(TestDirectQueue1()).to(TestDirectExchange()).with("TestDirectRouting");
    }

    //交换机
    @Bean
    DirectExchange yeeExchange() {
        return new DirectExchange("exchange.yee");
    }


    @Bean
    Binding bindingYeeExchangeMessage() {
        return BindingBuilder.bind(TestDirectQueue()).to(yeeExchange()).with(routingKey);
    }
}
