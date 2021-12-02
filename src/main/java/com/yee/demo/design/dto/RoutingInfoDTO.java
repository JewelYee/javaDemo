package com.yee.demo.design.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/9/1 10:55 AM
 */
@Data
public class RoutingInfoDTO implements Serializable {

    /**
     * 分账用户id
     */
    private String userId;

    /**
     * 分账类型（merchant-商户，agent-代理，sale-加盟 staff-员工 channel-渠道商 servicer-服务商)
     */
    private String routingType;

    /**
     * 是否货主(1-是，2-否)
     */
    private Integer cargoType;

    /**
     * 分账金额
     */
    private BigDecimal routingAmount;

    /**
     * 营收抵扣金额（退款时会区分正负）
     */
    private BigDecimal revenueAmount;

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"routingType\":\"")
                .append(routingType).append('\"');
        sb.append(",\"cargoType\":")
                .append(cargoType);
        sb.append(",\"routingAmount\":")
                .append(routingAmount);
        sb.append(",\"revenueAmount\":")
                .append(revenueAmount);
        sb.append('}');
        return sb.toString();
    }
}
