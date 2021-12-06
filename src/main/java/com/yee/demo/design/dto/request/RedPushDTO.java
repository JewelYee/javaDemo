package com.yee.demo.design.dto.request;

import lombok.Data;


/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/9/1 2:02 PM
 */
@Data
public class RedPushDTO extends BaseRequestDTO {

    private String newTradeNo;
    private String newBatchNo;

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"newTradeNo\":\"")
                .append(newTradeNo).append('\"');
        sb.append(",\"newBatchNo\":\"")
                .append(newBatchNo).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
