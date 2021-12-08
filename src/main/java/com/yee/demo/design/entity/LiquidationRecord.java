package com.yee.demo.design.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
@TableName("t_liquidation_record")
public class LiquidationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    @TableField(exist = false)
    private Long accountId;

    /**
     * 规则条件
     */
    private String ruleCondition;

    @TableField(exist = false)
    private String remark;

    @TableField(exist = false)
    private String ruleName;

    @TableField(exist = false)
    private String merchantId;

    @TableField(exist = false)
    private String settlementChannel;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"tradeNo\":\"")
                .append(tradeNo).append('\"');
        sb.append(",\"expenseId\":")
                .append(expenseId);
        sb.append(",\"ruleId\":")
                .append(ruleId);
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
        sb.append(",\"ruleCondition\":\"")
                .append(ruleCondition).append('\"');
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append(",\"merchantId\":\"")
                .append(merchantId).append('\"');
        sb.append(",\"settlementChannel\":\"")
                .append(settlementChannel).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"updateTime\":\"")
                .append(updateTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
