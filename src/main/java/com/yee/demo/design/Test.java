package com.yee.demo.design;

import com.yee.demo.design.dto.request.ChannelFeeDTO;
import com.yee.demo.design.dto.resp.LiquidationRecord;
import com.yee.demo.design.strategy1.COnlineRechargeStrategy;
import com.yee.demo.design.strategy1.SceneStrategy;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/2 5:09 PM
 */
public class Test {

    public static void main(String[] args) {
        ChannelFeeDTO channelFeeDTO = new ChannelFeeDTO();
        channelFeeDTO.setAmount(new BigDecimal("100"));
        channelFeeDTO.setMerchantId("111");
        channelFeeDTO.setRequestNo("requestNo");
        channelFeeDTO.setBatchNo("batchNo");
        channelFeeDTO.setTradeNo("tradeNo");
        SceneStrategy strategy = new COnlineRechargeStrategy();
        List<LiquidationRecord> liquidation = strategy.liquidation(channelFeeDTO);
        System.out.println(liquidation);
    }
}
