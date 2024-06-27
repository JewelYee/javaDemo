package com.yee.demo.rabbitmq.work;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2022/3/5 6:26 PM
 */
@SpringBootConfiguration
public class WorkRabbitConfig {

    // 配置一个工作模型队列
    @Bean
    public Queue queueWork1() {
        return new Queue("queue_work");
    }
}
