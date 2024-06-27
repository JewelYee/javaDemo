package com.yee.demo.spider;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yee
 * 2024/6/26 11:06
 */
public class WebCrawler {
    public static void main(String[] args) {
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

            }
        }catch (Exception e){

        }
    }
}
