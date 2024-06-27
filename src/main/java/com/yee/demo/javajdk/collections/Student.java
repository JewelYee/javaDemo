package com.yee.demo.javajdk.collections;

import java.math.BigDecimal;
import lombok.Data;


/**
 * @Desciption:
 * @author yee
 * @Date:2020/9/10 3:26 PM
 */
@Data
public class Student {

    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public Student(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }

}
