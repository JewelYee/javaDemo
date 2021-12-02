package com.yee.demo.design.handler;

import com.yee.demo.design.dto.BaseRequestDTO;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/2 4:13 PM
 */
public abstract class AbstractHandler {


    protected AbstractHandler chain;

    public void next(AbstractHandler handler){
        this.chain = handler;
    }

    public abstract void doHandle(BaseRequestDTO baseRequest);
}
