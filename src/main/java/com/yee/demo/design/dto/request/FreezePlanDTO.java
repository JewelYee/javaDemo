package com.yee.demo.design.dto.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desciption: 冻结方案
 * @Auther: yee
 * @Date:2021/8/31 2:10 PM
 */
@Data
public class FreezePlanDTO extends BaseRequestDTO {

    /**
     * 操作金额
     */
    private BigDecimal amount;

    /**
     * 1.冻结 2.解冻
     */
    private Integer type;
}
