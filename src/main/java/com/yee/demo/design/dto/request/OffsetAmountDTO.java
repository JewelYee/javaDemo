package com.yee.demo.design.dto.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desciption: 抵扣货款
 * @Auther: yee
 * @Date:2021/8/31 2:10 PM
 */
@Data
public class OffsetAmountDTO  extends BaseRequestDTO {

    /**
     * 抵扣金额
     */
    private BigDecimal amount;
}
