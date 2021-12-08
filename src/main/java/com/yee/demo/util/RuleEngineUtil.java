package com.yee.demo.util;

import com.alibaba.fastjson.JSON;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.entity.EntryRules;
import lombok.extern.slf4j.Slf4j;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/9/2 3:26 PM
 */
@Slf4j
public class RuleEngineUtil {

    private static ExpressRunner runner = new ExpressRunner();

    public static Object match(EntryRules entryRules, BaseRequestDTO dto ) {
        Object result = null;
        try {
            DefaultContext<String, Object> context = new DefaultContext<>();
            context.put("dto", dto);
            String ruleCondition = entryRules.getRuleCondition();
            result = runner.execute(ruleCondition, context, null, true, false);
        }catch (Exception e){
            log.error("入账规则匹配错误 tradeNo:{} param:{} result:{}", dto.getTradeNo(), dto.toString(), JSON.toJSONString(result));
            log.error("入账规则匹配error:",e);
        }
        return result;
    }
}
