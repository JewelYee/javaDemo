package com.yee.demo.rabbitmq.pulish;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2022/3/5 4:52 PM
 */
@SpringBootConfiguration
public class PulishRabbitConfig {

    // 发布订阅模式
    // 声明两个队列
    @Bean
    public Queue queueFanout1() {
        return new Queue("queue_fanout1");
    }
    @Bean
    public Queue queueFanout2() {
        return new Queue("queue_fanout2");
    }

    // 准备一个交换机
    @Bean
    public FanoutExchange exchangeFanout() {
        return new FanoutExchange("exchange_fanout");
    }
    // 将交换机和队列进行绑定
    @Bean
    public Binding bindingExchange1() {
        return BindingBuilder.bind(queueFanout1()).to(exchangeFanout());
    }
    @Bean
    public Binding bindingExchange2() {

        return BindingBuilder.bind(queueFanout2()).to(exchangeFanout());
    }




}
