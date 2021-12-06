package com.yee.demo.design.strategy1;

import com.yee.demo.design.dto.resp.LiquidationRecord;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.request.TransferWithdrawalDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption: 线下转账
 * @Auther: yee
 * @Date:2021/8/31 2:07 PM
 */
@Data
public class TransferWithdrawalStrategy implements SceneStrategy{


    @Override
    public List<LiquidationRecord> liquidation(BaseRequestDTO baseRequestDTO) {
        List<LiquidationRecord> liquidationRecords = new ArrayList<>();
        //spring.getBean
        TransferWithdrawalDTO channelFeeDTO = (TransferWithdrawalDTO) baseRequestDTO;

        if (channelFeeDTO.getAmount().compareTo(BigDecimal.ZERO) != 0) {
            LiquidationRecord liquidationRecord = new LiquidationRecord();
            liquidationRecord.setAmount(channelFeeDTO.getAmount());
            liquidationRecord.setAccountType("B_BALANCE");
            liquidationRecord.setRemark(channelFeeDTO.getRemark());
            liquidationRecord.setCaptitalDirection(2);
            liquidationRecords.add(liquidationRecord);

            liquidationRecord = new LiquidationRecord();
            liquidationRecord.setAccountType("ZMKJ_OFFLINE_TRANSFER");
            liquidationRecord.setCaptitalDirection(1);
            liquidationRecords.add(liquidationRecord);

            liquidationRecord = new LiquidationRecord();
            liquidationRecord.setAccountType("ZMKJ_OFFLINE_TRANSFER");
            liquidationRecord.setCaptitalDirection(2);
            liquidationRecords.add(liquidationRecord);

        }

        if (channelFeeDTO.getWithdrawalServerFee().compareTo(BigDecimal.ZERO) != 0) {
            LiquidationRecord liquidationRecord = new LiquidationRecord();
            liquidationRecord.setAccountType("ZMKJ_OFFLINE_TRANSFER");
            liquidationRecord.setCaptitalDirection(1);
            liquidationRecords.add(liquidationRecord);
        }
        return liquidationRecords;
    }

}
