package com.yee.demo.design.enums;

import com.yee.demo.design.dto.BaseRequestDTO;
import com.yee.demo.design.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 账户类型
 */
@AllArgsConstructor
@Getter
public enum ScenesItemEnum {
    /**
     * 内部记账场景
     */
    C_ONLINE_RECHARGE(1, "线上充值","1", ChargeTypeEnum.NORMAL_CHARGE, ChannelFeeDTO.class),
    C_ONLINE_WITHDRAWAL(2, "线上提现","1", ChargeTypeEnum.NORMAL_CHARGE, ChannelFeeDTO.class),
    TRANSFER_WITHDRAWAL(3, "线下转账提现","2", ChargeTypeEnum.PRE_CHARGE, TransferWithdrawalDTO.class),
    B_ONLINE_WITHDRAWAL(4, "B端线上提现","2", ChargeTypeEnum.PRE_CHARGE, BOnlineWithdrawalDTO.class),
    ORDER_SPLIT(5, "订单分账","1", ChargeTypeEnum.NORMAL_CHARGE, OrderDTO.class),
    ORDER_REFUND(6, "订单退款","2", ChargeTypeEnum.PRE_CHARGE, OrderDTO.class),
    TRANSFER(7, "上下级划扣","2", ChargeTypeEnum.PRE_CHARGE, TransferDTO.class),
    OFFSET_AMOUNT(8, "冲抵货款","2", ChargeTypeEnum.PRE_CHARGE, OffsetAmountDTO.class),
    FREEZE_PLAN(9, "冻结方案","1", ChargeTypeEnum.NORMAL_CHARGE, FreezePlanDTO.class),
    DEBT_REPAYMENT(10, "自动偿还","2", ChargeTypeEnum.PRE_CHARGE, DebtRepaymentDTO.class),
    ADVANCE_FUNDS(11, "垫资","1", ChargeTypeEnum.NORMAL_CHARGE, AdvancFundsDTO.class),
    CREDIT_ORDER_FEE(12, "信用订单渠道手续费","1", ChargeTypeEnum.NORMAL_CHARGE,ChannelFeeDTO.class),
    CREDIT_ORDER_FEE_RETURN(13, "信用订单渠道手续费退款","1", ChargeTypeEnum.NORMAL_CHARGE, ChannelFeeDTO.class),

    /**
     * B端业务记账场景
     */
    TRANSFER_WITHDRAWAL_B(101, "线下转账提现","2", ChargeTypeEnum.PRE_CHARGE, TransferWithdrawalDTO.class),
    B_ONLINE_WITHDRAWA_B(102, "B端线上提现","2", ChargeTypeEnum.PRE_CHARGE, BOnlineWithdrawalDTO.class),
    ORDER_SPLIT_B(103, "订单分账","2", ChargeTypeEnum.PRE_CHARGE, OrderDTO.class),
    ORDER_REFUND_B(104, "订单退款","2", ChargeTypeEnum.PRE_CHARGE, OrderDTO.class),
    TRANSFER_B(105, "上下级划扣","2", ChargeTypeEnum.PRE_CHARGE, TransferDTO.class),
    OFFSET_AMOUNT_B(106, "平台操作-冲抵货款","2", ChargeTypeEnum.PRE_CHARGE, OffsetAmountDTO.class),
    FREEZE_PLAN_B(107, "冻结方案","1", ChargeTypeEnum.NORMAL_CHARGE, FreezePlanDTO.class);


    @Override
    public String toString() {
        return desc + "(" + code + ")";
    }

    private Integer code;
    private String desc;
    private String bussiness;
    private ChargeTypeEnum chargeTypeEnum;
    private Class<? extends BaseRequestDTO> baseDTO;


    private static Map<Integer, ScenesItemEnum> enumMap;

    static {
        enumMap = Arrays.stream(ScenesItemEnum.values())
                .collect(Collectors.toMap(ScenesItemEnum::getCode, typeEnum -> typeEnum));
    }

    public static ScenesItemEnum getEnum(Integer code) {
        return enumMap.get(code);
    }









}
