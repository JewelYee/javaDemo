package com.yee.demo.design.factory.factory_method.factory;

import com.yee.demo.design.factory.factory_method.product.COnlineRechargeProduct;
import com.yee.demo.design.factory.factory_method.product.Scenes;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/30 2:25 PM
 */
public class COnlineRechargeFactory implements AbstractFactory{
    @Override
    public Scenes newScene() {
        System.out.println("场景：C端线上提现");
        return new COnlineRechargeProduct();
    }
}
