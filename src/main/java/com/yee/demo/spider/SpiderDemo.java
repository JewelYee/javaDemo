package com.yee.demo.spider;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * @author yee
 * 2024/6/27 10:34
 */
public class SpiderDemo {

    final static String preffix = "/Users/linyijun/Desktop/spider/";
    final static String detaiPreffixUrl = "https://webapps.condusef.gob.mx/SIPRES/jsp";
    final static String subStringUrl = "../../jsp/home_publico.jsp?idins=";


    public static void main(String[] args) {
        writeDetail();
    }
    private static void write(String content, String fileName){
        try {
            if (StringUtils.isEmpty(fileName)) {
                fileName = "web.text";
            }else {
                fileName = fileName+".html";
            }
            FileWriter writer = new FileWriter(preffix+fileName);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String read(String fileName){
        try {
            String preffix = "/Users/linyijun/Desktop/spider/";
            if (StringUtils.isEmpty(fileName)) {
                fileName = "web.text";
            }else {
                fileName = fileName+".html";
            }
            File file = new File(preffix+fileName);
            FileInputStream fin=new FileInputStream(file);
            InputStreamReader in=new InputStreamReader(fin,"utf-8"); //获取输入流
            BufferedReader br = new BufferedReader(in);
            char[] buf=new char[fin.available()];
            br.read(buf); //br的效率更高，带缓冲
            in.close();
            fin.close();
            return String.valueOf(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeDetail(){
        String htmlContent = read(null);
        // 使用Jsoup解析HTML内容
        Document doc = Jsoup.parse(htmlContent);
        Elements trs = doc.select("table tr");
        for (Element tr : trs) {
            Elements tds = tr.select("td");
            for (Element td : tds) {
                if (td.select("a").size() > 0) {
                    Element a = td.select("a").first();
                    String onclick = a.attr("onclick");
                    int startIndex = onclick.indexOf("window.open('") + "window.open('".length();
                    int endIndex = onclick.indexOf("'", startIndex);
                    if (startIndex != -1 && endIndex != -1) {
                        String httpUrl = onclick.substring(startIndex, endIndex);
                        String httpGetUrl = httpUrl.replace("../../jsp", detaiPreffixUrl);
                        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                            HttpGet httpGet = new HttpGet(httpGetUrl);
                            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                                HttpEntity entity = response.getEntity();
                                String detailContent = EntityUtils.toString(entity, "UTF-8");
                                int index = httpUrl.indexOf(subStringUrl);
                                if (index != -1) {
                                    String result = httpUrl.substring(index + subStringUrl.length());
                                    write(detailContent, result);
                                }

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }
}
