package com.yee.demo.design.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 清算流水 
 * </p>
 *
 * @author yee
 * @since 2021-08-26
 */

@Data
public class LiquidationRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;


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


    private String remark;

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
