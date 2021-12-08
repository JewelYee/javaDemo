package com.yee.demo.design.strategy1;

import com.yee.demo.design.dto.resp.LiquidationRecord;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.request.ChannelFeeDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption: C端线上提现
 * @Auther: yee
 * @Date:2021/12/2 5:32 PM
 */
public class ConlineWithdrawalStrategy implements SceneStrategy  {
    @Override
    public List<LiquidationRecord> liquidation(BaseRequestDTO baseRequestDTO) {
        List<LiquidationRecord> liquidationRecords = new ArrayList<>();
        //spring.getBean
        ChannelFeeDTO channelFeeDTO = (ChannelFeeDTO) baseRequestDTO;
        COnlineRechargeStrategy.channelFeeResult(liquidationRecords, channelFeeDTO);
        return liquidationRecords;
    }
}
