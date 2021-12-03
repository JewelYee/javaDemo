package com.yee.demo.design.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desciption: 上下级划扣
 * @Auther: yee
 * @Date:2021/8/31 2:09 PM
 */
@Data
public class TransferDTO  extends BaseRequestDTO {

    private BigDecimal amount;
    private String superUserId;
    private Long superAccountId;
    private String subUserId;
    private Long subAccountId;


    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"amount\":")
                .append(amount);
        sb.append(",\"superUserId\":\"")
                .append(superUserId).append('\"');
        sb.append(",\"superAccountId\":")
                .append(superAccountId);
        sb.append(",\"subUserId\":\"")
                .append(subUserId).append('\"');
        sb.append(",\"subAccountId\":")
                .append(subAccountId);
        sb.append('}');
        return sb.toString();
    }
}
