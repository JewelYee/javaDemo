package com.yee.demo.design.strategy1;

import com.yee.demo.design.dto.resp.LiquidationRecordDTO;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.request.ChannelFeeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption: C端线上提现
 * @Auther: yee
 * @Date:2021/12/2 5:32 PM
 */
public class ConlineWithdrawalStrategy implements SceneStrategy  {
    @Override
    public List<LiquidationRecordDTO> liquidation(BaseRequestDTO baseRequestDTO) {
        List<LiquidationRecordDTO> liquidationRecordDTOS = new ArrayList<>();
        //spring.getBean
        ChannelFeeDTO channelFeeDTO = (ChannelFeeDTO) baseRequestDTO;
        COnlineRechargeStrategy.channelFeeResult(liquidationRecordDTOS, channelFeeDTO);
        return liquidationRecordDTOS;
    }
}
