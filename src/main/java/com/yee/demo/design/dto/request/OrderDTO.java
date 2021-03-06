package com.yee.demo.design.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Desciption: 订单分账\订单退款
 * @Auther: yee
 * @Date:2021/8/31 2:08 PM
 */
@Data
public class OrderDTO extends BaseRequestDTO {

    /**
     * C端用户userId
     */
    private String userId;

    /**
     * 宝丢失收入金额
     */
    private BigDecimal lostAmount;

    /**
     * 分账回收服务费
     */
    private BigDecimal recoveryServiceFee;

    /**
     * 提现服务费
     */
    private BigDecimal serviceFee;

    /**
     * 结算渠道
     */
    private String settlementChannel;

    /**
     * 分账信息
     */
    private List<RoutingInfoDTO> routingInfoDTOList;

}
