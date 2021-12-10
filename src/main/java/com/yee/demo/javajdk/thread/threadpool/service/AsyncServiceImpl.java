package com.yee.demo.javajdk.thread.threadpool.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/12/10 10:38 AM
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {



    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        log.info("start executeAsync");

        System.out.println("异步线程要做的事情");
        System.out.println("可以在这里执行批量插入等耗时的事情");

        log.info("end executeAsync");
    }
}
