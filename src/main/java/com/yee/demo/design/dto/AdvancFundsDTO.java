package com.yee.demo.design.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/9/1 9:00 PM
 */
@Data
public class AdvancFundsDTO extends BaseRequestDTO {


    /**
     * 退款垫资金额
     */
    private BigDecimal refundAdvance;

}
