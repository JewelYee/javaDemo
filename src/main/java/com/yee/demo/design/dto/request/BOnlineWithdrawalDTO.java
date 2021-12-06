package com.yee.demo.design.dto.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desciption: B端线上提现
 * @Auther: yee
 * @Date:2021/8/31 2:07 PM
 */
@Data
public class BOnlineWithdrawalDTO extends BaseRequestDTO {



    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 提现手续费
     */
    private BigDecimal withdrawalServerFee;

}
