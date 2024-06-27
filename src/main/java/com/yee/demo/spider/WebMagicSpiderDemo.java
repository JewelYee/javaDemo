package com.yee.demo.spider;

import com.alibaba.fastjson.JSON;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author yee
 * 2024/6/26 10:23
 */
public class WebMagicSpiderDemo implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);


    public WebMagicSpiderDemo() {
        // 配置爬虫的User-Agent和其他参数
        site = Site.me()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
                .setRetryTimes(3)
                .setSleepTime(1000);
    }

    @Override
    public void process(Page page) {
        // 解析当前页面的table元素
        List<String> trs = page.getHtml().xpath("//table[@class='table table-striped table-responsiv']/tbody/tr").all();
        // 遍历每个<tr>元素
        for (String tr : trs) {
            // 解析<tr>中的每个<td>元素
            List<String> tds = page.getHtml().xpath(tr + "/td").all();
            // 假设tds中的索引是固定的，根据你的页面结构进行调整
            String firstTd = tds.get(0).trim();
            String secondTd = tds.get(1).trim();
            String thirdTd = tds.get(2).trim();
            String fourthTd = tds.get(3).trim();
            String fiveTd = tds.get(4).trim();
            String sixTd = tds.get(5).trim();
            String sevenTd = tds.get(6).trim();
            System.out.println("firstTd:"+firstTd);
            System.out.println("secondTd:"+secondTd);
            System.out.println("thirdTd:"+thirdTd);
            System.out.println("fourthTd:"+fourthTd);
            System.out.println("fiveTd:"+fiveTd);
            System.out.println("sixTd:"+sixTd);
            System.out.println("sevenTd:"+sevenTd);

            String link = page.getHtml().xpath(secondTd + "/a/@href").get();
            if (link != null && link.startsWith("#")) {
                // 假设onclick中的URL是相对于当前页面的，我们需要拼接完整的URL
                // 注意：这里只是示例，实际情况可能需要根据页面结构进行调整
                String jsUrl = page.getHtml().xpath(secondTd + "/a/@onclick")
                        .regex("window\\.open\\('(.*?)','','scrollbars=1,resizable=1'\\)")
                        .get();
                String httpGetUrl = jsUrl.replace("../../jsp", "https://webapps.condusef.gob.mx/SIPRES/jsp");

                System.out.println("httpGetUrl:"+httpGetUrl);
            }

        }
    }

    // 解析详细页面
    private void processDetailPage(Page page) {
        // 提取详细页面的信息
        String shortName = page.getHtml().xpath("//div[@id='div_generales']/div/div[1]/div[1]/text()").get();
        String status = page.getHtml().xpath("//div[@id='div_generales']/div/div[1]/div[2]/b/text()").get();
        String registrationCode = page.getHtml().xpath("//div[@id='div_generales']/div/div[1]/div[3]/text()").get();
        String email = page.getHtml().xpath("//div[@id='div_generales']/div/div[2]/div[1]/a/text()").get();
        String sipresDate = page.getHtml().xpath("//div[@id='div_generales']/div/div[2]/div[2]/text()").get();
        String sicDate = page.getHtml().xpath("//div[@id='div_generales']/div/div[3]/div/text()").get();

        // 输出结果到控制台（或存储到数据库/文件等）
        System.out.println("ShortName: " + shortName);
        System.out.println("Status: " + status);
        System.out.println("Registration Code: " + registrationCode);
        System.out.println("Email: " + email);
        System.out.println("SIPRES Date: " + sipresDate);
        System.out.println("SIC Date: " + sicDate);
    }
    @Override
    public Site getSite() {

        return site;
    }

    public static void main(String[] args) {
        // 创建爬虫
        Spider spider = Spider.create(new WebMagicSpiderDemo())
                .addUrl("https://webapps.condusef.gob.mx/SIPRES/jsp/pub/resulbusq.jsp")
                .thread(5)
                .setScheduler(new QueueScheduler());

        // 运行爬虫
        spider.run();
    }

}
