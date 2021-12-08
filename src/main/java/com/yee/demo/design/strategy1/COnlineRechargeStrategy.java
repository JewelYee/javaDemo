package com.yee.demo.design.strategy1;

import com.yee.demo.design.dto.resp.LiquidationRecordDTO;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.request.ChannelFeeDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption: C端线上充值
 * @Auther: yee
 * @Date:2021/12/2 4:18 PM
 */
public class COnlineRechargeStrategy implements SceneStrategy {

    @Override
    public List<LiquidationRecordDTO> liquidation(BaseRequestDTO baseRequestDTO) {
        List<LiquidationRecordDTO> liquidationRecordDTOS = new ArrayList<>();
            //spring.getBean
            ChannelFeeDTO channelFeeDTO = (ChannelFeeDTO) baseRequestDTO;
        channelFeeResult(liquidationRecordDTOS, channelFeeDTO);
        return liquidationRecordDTOS;

    }

    static void channelFeeResult(List<LiquidationRecordDTO> liquidationRecordDTOS, ChannelFeeDTO channelFeeDTO) {

        if (channelFeeDTO.getAmount().compareTo(BigDecimal.ZERO) != 0) {
            LiquidationRecordDTO liquidationRecordDTO = new LiquidationRecordDTO();
            liquidationRecordDTO.setAmount(channelFeeDTO.getAmount());
            liquidationRecordDTO.setAccountType("PT_PAYMENT_CHANNEL_FEE");
            liquidationRecordDTO.setRemark(channelFeeDTO.getRemark());
            liquidationRecordDTO.setCaptitalDirection(2);
            liquidationRecordDTOS.add(liquidationRecordDTO);
        }
    }
}
