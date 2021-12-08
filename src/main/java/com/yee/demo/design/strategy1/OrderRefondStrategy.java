package com.yee.demo.design.strategy1;

import com.yee.demo.design.dto.resp.LiquidationRecordDTO;
import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.dto.request.OrderDTO;
import com.yee.demo.design.dto.request.RoutingInfoDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption: 订单退款（宝丢失逻辑和订单分账有区别）
 * @Auther: yee
 * @Date:2021/12/2 5:32 PM
 */
public class OrderRefondStrategy implements SceneStrategy {

    @Override
    public List<LiquidationRecordDTO> liquidation(BaseRequestDTO baseRequestDTO) {
        List<LiquidationRecordDTO> list = new ArrayList<>();


        OrderDTO orderDTO = (OrderDTO) baseRequestDTO;

        // 1.是否宝丢失
        //  1.2 是否第一次退款

        //      1.2.1 第一次退款
        //          1.2.1.1 退款金额小于宝成本，宝丢失收入账户吐出宝丢失金额，退还用户退款金额，剩余金额重新再分账，订单费、分账收回服务费重新再收一次
        //          1.2.1.2 退款金额大于宝成本，宝丢失收入账户吐出宝丢失金额，退还用户退款金额，剩余需要退款金额按照比例从分账方、订单费、分账收回服务费退回
        //      1.2.2 不是第一次退款
                    // 1.2.2.1 退还用户退款金额，按照比例从分账方、订单费、分账收回服务费退回
        BigDecimal recoveryServiceFee = orderDTO.getRecoveryServiceFee();
        if (recoveryServiceFee.compareTo(BigDecimal.ZERO) > 0) {
            LiquidationRecordDTO record1 = new LiquidationRecordDTO();
            record1.setAmount(recoveryServiceFee);
            record1.setExpenseName("分账收回服务费");
            record1.setAccountType("PT_SPLIT_RECECIVE_CHANNEL_FEE");
            list.add(record1);
        }

        if (orderDTO.getServiceFee().compareTo(BigDecimal.ZERO) != 0){
            LiquidationRecordDTO record2 = new LiquidationRecordDTO();
            record2.setAmount(orderDTO.getServiceFee());
            record2.setExpenseName("提现服务费");
            record2.setAccountType("PT_WITHDRAWAL_SERVICE_FEE");
            list.add(record2);
        }


        BigDecimal lostAmount = orderDTO.getLostAmount();
        if (lostAmount.compareTo(BigDecimal.ZERO) != 0){
            LiquidationRecordDTO record3 = new LiquidationRecordDTO();
            record3.setAmount(lostAmount);
            record3.setExpenseName("宝丢失收入");
            record3.setAccountType("PT_LOST_ORDER_PAY");
            list.add(record3);
        }


        List<RoutingInfoDTO> routingInfoDTOList = orderDTO.getRoutingInfoDTOList();
        routingInfoDTOList.forEach(e -> {
            // 营收抵扣
            BigDecimal revenueAmount = e.getRevenueAmount();
            if (revenueAmount.compareTo(BigDecimal.ZERO) != 0) {
                LiquidationRecordDTO record4 = new LiquidationRecordDTO();
                record4.setAccountType("B_BALANCE");
                record4.setExpenseName("货款收入-营收抵扣");
                record4.setAmount(revenueAmount);
                list.add(record4);
                LiquidationRecordDTO record5 = new LiquidationRecordDTO();
                record5.setAccountType("PT_EVENUE_RATIO");
                record5.setExpenseName("支付货款-营收抵扣");
                record5.setAmount(revenueAmount);
                list.add(record4);
            }
            // 分账金额
            LiquidationRecordDTO record6 = new LiquidationRecordDTO();
            BigDecimal routingAmount = e.getRoutingAmount();
            record6.setUserId(e.getUserId());
            record6.setAccountType("B_BALANCE");
            if (!(e.getCargoType() == 2 && e.getRoutingType().equalsIgnoreCase("agent"))) {
                record6.setExpenseName("设备收益");
                record6.setAmount(routingAmount);
            }else {
                record6.setExpenseName("加盟商贡献");
                record6.setAmount(routingAmount);

            }
            list.add(record6);
        });

        return list;
    }
}
