package com.yee.demo.design.factory.factory_method.factory;

import com.yee.demo.design.factory.factory_method.product.Scenes;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/30 2:24 PM
 */
public interface AbstractFactory {
    Scenes newScene();
}
