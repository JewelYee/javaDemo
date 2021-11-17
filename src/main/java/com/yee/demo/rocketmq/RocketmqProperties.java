package com.yee.demo.rocketmq;

import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.yee.demo.rocketmq.RocketmqProperties.PREFIX;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020/10/27 2:40 PM
 */
@Data
@ConfigurationProperties(PREFIX)
public class RocketmqProperties {

    public static final String PREFIX = "spring.extend.rocketmq";

    private String namesrvAddr;
    private String instanceName;
    private String clientIP;
    private ProducerConfig producer;
    private ConsumerConfig consumer;

}

