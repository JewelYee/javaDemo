package com.yee.demo.design.dto.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/9/3 2:27 PM
 */
@Data
public class ChannelFeeDTO extends BaseRequestDTO {

    /**
     * 渠道手续费
     */
    private BigDecimal amount;
    private String merchantId;

}
