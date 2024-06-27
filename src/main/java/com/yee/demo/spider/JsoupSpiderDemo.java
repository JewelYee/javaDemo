package com.yee.demo.spider;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author yee
 * 2024/6/26 10:22
 */
public class JsoupSpiderDemo {
    public static void main(String[] args) {
        try {
            // 发送HTTP GET请求并获取网页内容
//            Document document = Jsoup.connect("https://webapps.condusef.gob.mx/SIPRES/jsp/pub/index.jsp#14981").get();
            Document document = Jsoup.connect("https://webapps.condusef.gob.mx/SIPRES/jsp/pub/resulbusq.jsp").get();

            // 获取网页标题
            Element body = document.body();
            System.out.println("【document】：" + JSON.toJSONString(document));
            System.out.println("【body】：" + body.text());
            body.html();
            System.out.println("【html】：" + body.html());
            body.html();

            // 获取所有的链接
//            Elements links = document.select("a[href]");
//            System.out.println("链接数量：" + links.size());

            // 打印每个链接的文本和URL
//            for (Element link : links) {
//                String linkText = link.text();
//                String linkUrl = link.attr("href");
//                System.out.println("链接文本：" + linkText);
//                System.out.println("链接URL：" + linkUrl);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
