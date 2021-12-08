package com.yee.demo.design.dto.resp;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResultDTO {

    private Boolean condition=false;
    private BigDecimal amount;
    private String userId;
    private Long accountId;
    /**
     * 结算渠道
     */
    private String settlementChannel;
    private String merchantId;


}
