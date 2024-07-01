package com.yee.demo.spider;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * @author yee
 * 2024/6/26 10:22
 */
public class JsoupSpiderDemo {
    final static String localUri = "/Users/linyijun/Desktop/spider/";

    public static void main(String[] args) {
        try {
            String htmlContent = read(null);
            Document document = Jsoup.parse(htmlContent);
            Elements links = document.select("a[href]");
            for (Element link : links) {
                String onclick = link.attr("onclick");
                String httpUrl = StrUtil.split(StrUtil.replace(onclick,"\"","'"),"'").get(1);
                String httpGetUrl = httpUrl.replace("../../jsp", "https://webapps.condusef.gob.mx/SIPRES/jsp");
                Document detailDocument = Jsoup.connect(httpGetUrl).get();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String read(String fileName){
        try {
            if (StringUtils.isEmpty(fileName)) {
                fileName = "web1.txt";
            }else {
                fileName = fileName+".html";
            }
            File file = new File(localUri + fileName);
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
}
