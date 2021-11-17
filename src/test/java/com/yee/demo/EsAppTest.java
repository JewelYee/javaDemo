package com.yee.demo;

import com.yee.demo.es.EsDemo;
import com.yee.demo.es.Tests;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020/10/13 4:11 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { YeeApplication.class }) // 指定启动类
public class EsAppTest {

    @Autowired
    private EsDemo esDemo;

    public static String INDEX_TEST = null;
    public static String TYPE_TEST = null;
    public static Tests tests = null;
    public static List<Tests> testsList = null;

    @BeforeClass
    public static void before() {
        INDEX_TEST = "index_test"; // 索引名称
        TYPE_TEST = "type_test"; // 索引类型
        testsList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tests = new Tests();
            tests.setId(Long.valueOf(i));
            tests.setName("this is the test " + i);
            testsList.add(tests);
        }
    }

    @Test
    public void testIndex() throws IOException {
//        // 判断是否存在索引
//        if (!esDemo.existsIndex(INDEX_TEST)) {
//            // 不存在则创建索引
//            esDemo.createIndex(INDEX_TEST);
//            System.out.println("createIndex");
//        }
//        System.out.println("-------");

    }






}
