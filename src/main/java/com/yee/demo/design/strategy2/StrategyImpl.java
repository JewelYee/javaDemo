package com.yee.demo.design.strategy2;

import com.google.common.collect.Lists;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.resp.LiquidationRecord;
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
    public List<LiquidationRecord> liquidation(BaseRequestDTO dto) {
        List<LiquidationRecord> resultList = Lists.newArrayList();

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
        return resultList;
    }

    private void convertLiquidation(ResultDTO resultDTO, EntryRules entryRule, BaseRequestDTO dto, List<LiquidationRecord> resultList){
        if (resultDTO.getCondition()) {
            String accountType = entryRule.getAccountType();
            Integer captitalDirection = entryRule.getCaptitalDirection();
            Long accountId = resultDTO.getAccountId();
            BigDecimal amount = resultDTO.getAmount();
            String userId = resultDTO.getUserId();

            // 3.记账
            LiquidationRecord liquidationRecord = new LiquidationRecord();
            liquidationRecord.setAccountType(StringUtils.lowerCase(accountType));
            liquidationRecord.setBatchNo(dto.getBatchNo() == null ? " " : dto.getBatchNo());
            liquidationRecord.setAmount(amount);
            liquidationRecord.setCaptitalDirection(captitalDirection);
            liquidationRecord.setExpenseId(entryRule.getExpenseId());
            liquidationRecord.setRuleCondition(entryRule.getRuleCondition());
            liquidationRecord.setRuleId(entryRule.getId());
            liquidationRecord.setUserId(userId == null ? "" : userId );
            liquidationRecord.setAccountId(accountId);
            liquidationRecord.setRuleName(entryRule.getRuleName());
            liquidationRecord.setTradeNo(dto.getTradeNo());
            liquidationRecord.setCreateTime(new Date());
            liquidationRecord.setUpdateTime(new Date());
            liquidationRecord.setMerchantId(resultDTO.getMerchantId());
            liquidationRecord.setSettlementChannel(resultDTO.getSettlementChannel());
            liquidationRecord.setRemark(dto.getRemark() == null ? "" : dto.getRemark());
            resultList.add(liquidationRecord);
        }
    }
}
