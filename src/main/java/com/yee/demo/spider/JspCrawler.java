package com.yee.demo.spider;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yee
 * 2024/6/26 10:48
 */
public class JspCrawler {
    final static String preffix = "/Users/linyijun/Desktop/spider/";
    final static String detaiPreffixUrl = "https://webapps.condusef.gob.mx/SIPRES/jsp";
    public static void main(String[] args) {

        List<List<Object>> dataList = ListUtils.newArrayList();
        String url = "https://webapps.condusef.gob.mx/SIPRES/jsp/pub/resulbusq.jsp";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tipo", "1"));
        params.add(new BasicNameValuePair("pnom", "SOFOM"));

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                String htmlContent = EntityUtils.toString(entity, "UTF-8");
                // 使用Jsoup解析HTML内容
                Document doc = Jsoup.parse(htmlContent);
                Elements trs = doc.select("table tr");
                int i=1;
                for (Element tr : trs) {

                    List<Object> rowValueList = ListUtils.newArrayList();

                    Elements tds = tr.select("td");
                    for (Element td : tds) {
                        if (td.select("a").size() > 0) {
                            rowValueList.add(td.text());
                            childHtml(td, rowValueList);
                        }else{
                            rowValueList.add(td.text());
                        }
                    }
                    dataList.add(rowValueList);


                    if (dataList.size() == 5){
                        System.out.println("========= 开始写excel =========");
                        EasyExcel.write("/Users/linyijun/Desktop/test.xlsx").sheet("模板").doWrite(dataList);
                        return;
                    }
                    i++;
                }
            }
//            System.out.println("========= 开始写excel =========");
//            EasyExcel.write("/Users/linyijun/Desktop/test.xlsx").sheet("模板").doWrite(dataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void readHtml(String htmlContent){
        List<List<Object>> dataList = ListUtils.newArrayList();
        // 使用Jsoup解析HTML内容
        Document doc = Jsoup.parse(htmlContent);
        Elements trs = doc.select("table tr");
        int i=1;
        for (Element tr : trs) {

            List<Object> rowValueList = ListUtils.newArrayList();

            Elements tds = tr.select("td");
            for (Element td : tds) {
                if (td.select("a").size() > 0) {
                    rowValueList.add(td.text());
                    childHtml(td, rowValueList);
                }else{
                    rowValueList.add(td.text());
                }
            }
            dataList.add(rowValueList);


            if (dataList.size() == 5){
                System.out.println("========= 开始写excel =========");
                EasyExcel.write("/Users/linyijun/Desktop/test.xlsx").sheet("模板").doWrite(dataList);
                return;
            }
            i++;
        }
    }
    private static List<Object> childHtml(Element td, List<Object> rowValueList) {
        Element a = td.select("a").first();
        String onclick = a.attr("onclick");
        int startIndex = onclick.indexOf("window.open('") + "window.open('".length();
        int endIndex = onclick.indexOf("'", startIndex);
        if (startIndex != -1 && endIndex != -1) {
            String httpUrl = onclick.substring(startIndex, endIndex);
            String httpGetUrl = httpUrl.replace("../../jsp", "https://webapps.condusef.gob.mx/SIPRES/jsp");
            return processDetail(httpGetUrl, rowValueList);
        }
        return rowValueList;
    }

    private static List<Object> processDetail(String url, List<Object> rowValueList) {
        try {
            // 使用Jsoup获取HTML文档
            Document document = Jsoup.connect(url).get();

            // 提取图片链接
            Element imgElement = document.select("#div_generales #divlogo #logoins").first();
            String imgSrc = imgElement != null ? imgElement.absUrl("src") : null;
            if (imgSrc != null) {
                rowValueList.add(imgSrc);
            }
            Elements rows = document.select("#div_generales .row");
            for (Element row : rows) {
                // 遍历row元素下的col-sm-*类的div元素
                Elements cols = row.select("div[class^='col-sm-']");
                for (Element col : cols) {
                    // 查找<b>标签并获取其文本内容
                    Element bTag = col.select("b").first();
                    Element hrTag = col.select("hr").first();
                    String text="";
                    if (hrTag != null) {
                        for (Node node : hrTag.siblingNodes()) {
                            if (node instanceof org.jsoup.nodes.TextNode) {
                                // 如果节点是TextNode，则获取其文本并去除前后的空白字符
                                text = ((TextNode) node).text().trim();
                            }
                        }
                    }

                    if (StringUtils.isEmpty(text)&& hrTag!=null){
                        StringBuilder textAfterHr = new StringBuilder();
                        Element nextSibling = hrTag.nextElementSibling();
                        while (nextSibling != null && !(nextSibling.tagName().equals("b") || nextSibling.tagName().equals("hr"))) {
                            // 累加文本内容，包括子元素的文本
                            textAfterHr.append(nextSibling.text()).append(" ");
                            nextSibling = nextSibling.nextElementSibling();
                        }
                        text = textAfterHr.toString().trim();
                    }
                    if (StringUtils.isEmpty(text)&& hrTag!=null && hrTag.nextElementSibling()!=null){
                        text = hrTag.nextElementSibling().text();
                    }
                    rowValueList.add(text);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowValueList;
    }

    private static void downloadImage(String imageUrl, String destFilePath) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        try (java.io.InputStream inputStream = httpConn.getInputStream();
             java.io.FileOutputStream outputStream = new java.io.FileOutputStream(destFilePath)) {

            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } finally {
            httpConn.disconnect();
        }
    }


}
