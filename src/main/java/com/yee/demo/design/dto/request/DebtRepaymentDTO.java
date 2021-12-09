package com.yee.demo.design.dto.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desciption: 自动偿还
 * @Auther: yee
 * @Date:2021/8/31 2:11 PM
 */
@Data
public class DebtRepaymentDTO extends BaseRequestDTO {

    /**
     * 1-退款垫资偿还 2-抵扣货款偿还
     */
    private Integer type;

    /**
     * 偿还金额
     */
    private BigDecimal amount;
}
