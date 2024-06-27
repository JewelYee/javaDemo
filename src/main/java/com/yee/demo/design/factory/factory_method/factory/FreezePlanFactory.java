package com.yee.demo.design.factory.factory_method.factory;

import com.yee.demo.design.factory.factory_method.product.FreezePlanProduct;
import com.yee.demo.design.factory.factory_method.product.Scenes;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/30 2:41 PM
 */
public class FreezePlanFactory implements AbstractFactory {
    @Override
    public Scenes newScene() {
        System.out.println("场景：冻结方案");
        return new FreezePlanProduct();
    }
}
