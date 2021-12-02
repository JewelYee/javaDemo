package com.yee.demo.design.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 费用字典 
 * </p>
 *
 * @author yee
 * @since 2021-08-26
 */
@Data
public class ExpenseDictDTO implements Serializable {

    /**
     * 费用编码
     */
    private String code;
    /**
     * 费用名称
     */
    private String name;


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof ExpenseDictDTO)) return false;
        ExpenseDictDTO that = (ExpenseDictDTO) o;
        return Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCode(), getName());
    }
}
