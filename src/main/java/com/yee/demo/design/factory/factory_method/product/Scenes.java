package com.yee.demo.design.factory.factory_method.product;

import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.request.LiquidationRecordDTO;

import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/23 11:45 AM
 */
public interface Scenes {

    List<LiquidationRecordDTO> liquidation(BaseRequestDTO baseRequestDTO);
    BaseRequestDTO getScene();
    String rule();


}
