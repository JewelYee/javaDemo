package com.yee.demo.design.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/2 4:17 PM
 */
@Getter
public enum ChargeTypeEnum {
    PRE_CHARGE("PRE_CHARGE", "预记账"),
    NORMAL_CHARGE("NORMAL_CHARGE", "正常记账"),
    CONFIRM_CHARGE("CONFIRM_CHARGE", "确认记账"),
    CANCEL_CHARGE("CANCEL_CHARGE", "取消记账");

    private String code;
    private String desc;

    private ChargeTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ChargeTypeEnum getChargeTypeEnumByCode(String code) {
        return (ChargeTypeEnum)Arrays.stream(values()).filter((chargeTypeEnum) -> {
            return chargeTypeEnum.getCode().equals(code);
        }).findFirst().get();
    }
}
