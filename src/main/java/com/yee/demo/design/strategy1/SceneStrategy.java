package com.yee.demo.design.strategy1;

import com.yee.demo.design.dto.resp.LiquidationRecord;
import com.yee.demo.design.dto.request.BaseRequestDTO;

import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/2 4:34 PM
 */
public interface SceneStrategy {

    List<LiquidationRecord> liquidation(BaseRequestDTO baseRequestDTO);
}
