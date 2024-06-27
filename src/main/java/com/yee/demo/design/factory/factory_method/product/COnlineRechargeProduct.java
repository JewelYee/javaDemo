package com.yee.demo.design.factory.factory_method.product;

import com.google.common.collect.Lists;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.request.ChannelFeeDTO;
import com.yee.demo.design.dto.request.LiquidationRecordDTO;

import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/23 11:47 AM
 */
public class COnlineRechargeProduct implements Scenes {

    @Override
    public List<LiquidationRecordDTO> liquidation(BaseRequestDTO baseRequestDTO) {
        List<LiquidationRecordDTO> resultList = Lists.newArrayList();
        System.out.println("执行C端线上提现场景 清算逻辑");
        return resultList;
    }

    @Override
    public BaseRequestDTO getScene() {
        return null;
    }

    @Override
    public String rule() {

        return "C端线上提现规则";
    }

    public ChannelFeeDTO getScene(BaseRequestDTO baseRequestDTO) {
        ChannelFeeDTO dto = (ChannelFeeDTO) baseRequestDTO;
        return dto;
    }

}
