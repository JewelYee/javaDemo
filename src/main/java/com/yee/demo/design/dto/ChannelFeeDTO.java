package com.yee.demo.design.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/9/3 2:27 PM
 */
@Data
public class ChannelFeeDTO extends BaseRequestDTO {
    private BigDecimal amount;
    private String merchantId;


    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"amount\":")
                .append(amount);
        sb.append(",\"merchantId\":\"")
                .append(merchantId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
