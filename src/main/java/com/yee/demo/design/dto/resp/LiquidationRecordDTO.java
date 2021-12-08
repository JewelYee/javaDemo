package com.yee.demo.design.dto.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/2 4:56 PM
 */
@Data
public class LiquidationRecordDTO {


    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 交易id
     */
    private String tradeNo;

    /**
     * 流水号
     */
    private String batchNo;

    /**
     * 费用id
     */
    private Long expenseId;
    private String expenseName;

    /**
     * 规则id
     */
    private Long ruleId;

    /**
     * 账户枚举
     */
    private String accountType;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 资金方向1.收入 2.支出
     */
    private Integer captitalDirection;

    /**
     * 用户id
     */
    private String userId;

    private Long accountId;

    /**
     * 规则条件
     */
    private String ruleCondition;

    private String remark;

    private String ruleName;

    private String merchantId;

    private String settlementChannel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
