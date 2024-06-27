package com.yee.demo.design.factory.factory_method;

import com.yee.demo.design.dto.request.BaseRequestDTO;
import com.yee.demo.design.factory.factory_method.factory.AbstractFactory;
import com.yee.demo.design.factory.factory_method.factory.COnlineRechargeFactory;
import com.yee.demo.design.factory.factory_method.factory.FreezePlanFactory;
import com.yee.demo.design.factory.factory_method.product.Scenes;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/30 2:51 PM
 */
public class Test_factory_method {
    public static void main(String[] args) {

        AbstractFactory factory = new COnlineRechargeFactory();
        Scenes COnlineRechargeProduct = factory.newScene();
        BaseRequestDTO COnlineRechargeScene = COnlineRechargeProduct.getScene();
//        COnlineRechargeProduct.liquidation();


        AbstractFactory freezePlanFactory = new FreezePlanFactory();
        Scenes freezePlanProduct = freezePlanFactory.newScene();
//        freezePlanProduct.liquidation();

        System.out.println();
    }
}
