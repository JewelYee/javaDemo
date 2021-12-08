package com.yee.demo.design.strategy1;

import com.yee.demo.design.dto.resp.LiquidationRecordDTO;
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
    public List<LiquidationRecordDTO> liquidation(BaseRequestDTO baseRequestDTO) {
        List<LiquidationRecordDTO> liquidationRecordDTOS = new ArrayList<>();
        //spring.getBean
        TransferWithdrawalDTO transferWithdrawalDTO = (TransferWithdrawalDTO) baseRequestDTO;

        if (transferWithdrawalDTO.getAmount().compareTo(BigDecimal.ZERO) != 0) {
            LiquidationRecordDTO liquidationRecordDTO = new LiquidationRecordDTO();
            liquidationRecordDTO.setAmount(transferWithdrawalDTO.getAmount());
            liquidationRecordDTO.setAccountType("B_BALANCE");
            liquidationRecordDTO.setRemark(transferWithdrawalDTO.getRemark());
            liquidationRecordDTO.setCaptitalDirection(2);
            liquidationRecordDTOS.add(liquidationRecordDTO);

            liquidationRecordDTO = new LiquidationRecordDTO();
            liquidationRecordDTO.setAccountType("PT_OFFLINE_TRANSFER");
            liquidationRecordDTO.setCaptitalDirection(1);
            liquidationRecordDTOS.add(liquidationRecordDTO);

            liquidationRecordDTO = new LiquidationRecordDTO();
            liquidationRecordDTO.setAccountType("PT_OFFLINE_TRANSFER");
            liquidationRecordDTO.setCaptitalDirection(2);
            liquidationRecordDTOS.add(liquidationRecordDTO);

        }

        if (transferWithdrawalDTO.getWithdrawalServerFee().compareTo(BigDecimal.ZERO) != 0) {
            LiquidationRecordDTO liquidationRecordDTO = new LiquidationRecordDTO();
            liquidationRecordDTO.setAccountType("PT_OFFLINE_TRANSFER");
            liquidationRecordDTO.setCaptitalDirection(1);
            liquidationRecordDTOS.add(liquidationRecordDTO);
        }
        return liquidationRecordDTOS;
    }

}
