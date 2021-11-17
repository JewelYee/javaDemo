package com.yee.demo.rocketmq.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020/10/27 2:52 PM
 */
@Data
@AllArgsConstructor
public class RocketMQException extends RuntimeException{

    private int code;
    private String desc;

    private RocketMQErrorEnum errorEnum;

    public RocketMQException(RocketMQErrorEnum errorEnum, String desc) {

        this.desc = desc;
        this.errorEnum = errorEnum;
    }
    public RocketMQException( String desc) {
        this.code = -1;
        this.desc = desc;
    }

}
