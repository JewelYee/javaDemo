package com.yee.demo.design.dto.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desciption: 上下级划扣
 * @Auther: yee
 * @Date:2021/8/31 2:09 PM
 */
@Data
public class TransferDTO  extends BaseRequestDTO {

    /**
     * 划扣金额
     */
    private BigDecimal amount;

    /**
     * 上级
     */
    private String superUserId;
    private Long superAccountId;

    /**
     * 下级
     */
    private String subUserId;
    private Long subAccountId;

}
