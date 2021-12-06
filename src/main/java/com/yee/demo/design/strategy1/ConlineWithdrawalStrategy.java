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
        if (channelFeeDTO.getAmount().compareTo(BigDecimal.ZERO) != 0) {
            LiquidationRecord liquidationRecord = new LiquidationRecord();
            liquidationRecord.setAmount(channelFeeDTO.getAmount());
            liquidationRecord.setAccountType("ZMKJ_PAYMENT_CHANNEL_FEE");
            liquidationRecord.setRemark(channelFeeDTO.getRemark());
            liquidationRecord.setCaptitalDirection(2);
            liquidationRecords.add(liquidationRecord);
        }
        return liquidationRecords;
    }
}
