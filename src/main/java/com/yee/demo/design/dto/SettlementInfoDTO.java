package com.yee.demo.design.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/9/3 7:24 PM
 */
@Data
public class SettlementInfoDTO implements Serializable {


    /**
     * 费用code
     */
    private String expenseCode;

    /**
     * 费用名称
     */
    private String expenseName;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 场景id
     */
    private Long scenesId;

    /**
     * 场景name
     */
    private String scenesName;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 交易资金
     */
    private BigDecimal amount;

    /**
     * 资金方向1.收入 2.支出
     */
    private int captitalDirection;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 账户id
     */
    private Long accountId;


    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"expenseCode\":\"")
                .append(expenseCode).append('\"');
        sb.append(",\"expenseName\":\"")
                .append(expenseName).append('\"');
        sb.append(",\"ruleId\":")
                .append(ruleId);
        sb.append(",\"scenesId\":")
                .append(scenesId);
        sb.append(",\"accountType\":\"")
                .append(accountType).append('\"');
        sb.append(",\"amount\":")
                .append(amount);
        sb.append(",\"captitalDirection\":")
                .append(captitalDirection);
        sb.append(",\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"accountId\":")
                .append(accountId);
        sb.append('}');
        return sb.toString();
    }

}
