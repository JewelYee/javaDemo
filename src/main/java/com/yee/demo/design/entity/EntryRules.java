package com.yee.demo.design.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 入账规则 
 * </p>
 *
 * @author yee
 * @since 2021-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_entry_rules")
public class EntryRules implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 场景id
     */
    private Long sceneId;

    /**
     * 费用id
     */
    private Long expenseId;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则条件
     */
    private String ruleCondition;

    /**
     * 资金方向1.收入 2.支出
     */
    private Integer captitalDirection;

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
        sb.append(",\"sceneId\":")
                .append(sceneId);
        sb.append(",\"expenseId\":")
                .append(expenseId);
        sb.append(",\"accountType\":\"")
                .append(accountType).append('\"');
        sb.append(",\"ruleName\":\"")
                .append(ruleName).append('\"');
        sb.append(",\"ruleCondition\":\"")
                .append(ruleCondition).append('\"');
        sb.append(",\"captitalDirection\":")
                .append(captitalDirection);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"updateTime\":\"")
                .append(updateTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
