package com.yee.demo.design.strategy2;

import com.google.common.collect.Lists;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.resp.LiquidationRecordDTO;
import com.yee.demo.design.dto.resp.ResultDTO;
import com.yee.demo.design.entity.EntryRules;
import com.yee.demo.design.strategy1.SceneStrategy;
import com.yee.demo.util.RuleEngineUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Desciption: 订单分账
 * @Auther: yee
 * @Date:2021/12/2 5:32 PM
 */
@Slf4j
public class StrategyImpl implements SceneStrategy {

    @Override
    public List<LiquidationRecordDTO> liquidation(BaseRequestDTO dto) {
        List<LiquidationRecordDTO> resultList = Lists.newArrayList();

        Integer code = dto.getScenesItemEnum().getCode();
        // 根据场景code查询出匹配规则
        List<EntryRules> entryRules = Lists.newArrayList();

        entryRules.stream().forEachOrdered(entryRule ->{
            // 2.2.根据入账规则找到账户类型、金额方向
            Object resultObj = RuleEngineUtil.match(entryRule, dto);
            if (Objects.isNull(resultObj)) return;
            List<ResultDTO> resultDTOList = (List<ResultDTO>) resultObj;
            resultDTOList.forEach(resultDTO -> convertLiquidation(resultDTO, entryRule, dto, resultList));
        });
        // 1.不需要写那么多handler
        // 规则改变不大 比如要添加 什么其他费用不需要部署代码
        return resultList;
    }

    private void convertLiquidation(ResultDTO resultDTO, EntryRules entryRule, BaseRequestDTO dto, List<LiquidationRecordDTO> resultList){
        if (resultDTO.getCondition()) {
            String accountType = entryRule.getAccountType();
            Integer captitalDirection = entryRule.getCaptitalDirection();
            Long accountId = resultDTO.getAccountId();
            BigDecimal amount = resultDTO.getAmount();
            String userId = resultDTO.getUserId();

            // 3.记账
            LiquidationRecordDTO liquidationRecordDTO = new LiquidationRecordDTO();
            liquidationRecordDTO.setAccountType(StringUtils.lowerCase(accountType));
            liquidationRecordDTO.setBatchNo(dto.getBatchNo() == null ? " " : dto.getBatchNo());
            liquidationRecordDTO.setAmount(amount);
            liquidationRecordDTO.setCaptitalDirection(captitalDirection);
            liquidationRecordDTO.setExpenseId(entryRule.getExpenseId());
            liquidationRecordDTO.setRuleCondition(entryRule.getRuleCondition());
            liquidationRecordDTO.setRuleId(entryRule.getId());
            liquidationRecordDTO.setUserId(userId == null ? "" : userId );
            liquidationRecordDTO.setAccountId(accountId);
            liquidationRecordDTO.setRuleName(entryRule.getRuleName());
            liquidationRecordDTO.setTradeNo(dto.getTradeNo());
            liquidationRecordDTO.setCreateTime(new Date());
            liquidationRecordDTO.setUpdateTime(new Date());
            liquidationRecordDTO.setMerchantId(resultDTO.getMerchantId());
            liquidationRecordDTO.setSettlementChannel(resultDTO.getSettlementChannel());
            liquidationRecordDTO.setRemark(dto.getRemark() == null ? "" : dto.getRemark());
            resultList.add(liquidationRecordDTO);
        }
    }
}
