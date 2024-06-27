package com.yee.demo.design.factory.factory_method.product;

import com.google.common.collect.Lists;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.request.FreezePlanDTO;
import com.yee.demo.design.dto.request.LiquidationRecordDTO;

import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/30 2:22 PM
 */
public class FreezePlanProduct implements Scenes {

    @Override
    public List<LiquidationRecordDTO> liquidation(BaseRequestDTO baseRequestDTO) {
        List<LiquidationRecordDTO> resultList = Lists.newArrayList();
        System.out.println("执行冻结场景清算逻辑");
        return resultList;
    }

    @Override
    public BaseRequestDTO getScene() {

        return null;
    }

    @Override
    public String rule() {
        return "冻结方案规则";
    }

    public FreezePlanDTO getScene(BaseRequestDTO baseRequestDTO) {
        FreezePlanDTO freezePlanDTO = (FreezePlanDTO) baseRequestDTO;
        return freezePlanDTO;
    }


}
