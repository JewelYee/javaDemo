package com.yee.demo.es;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020/10/13 4:01 PM
 */

//@SpringBootConfiguration
public class EsClientConfiguration {

//    @Value("${spring.elasticsearch.rest.uris}")
    private String[] esUris;

//    @Value("${spring.elasticsearch.rest.timeout:1000000}")
    private int timeout;

//    @Value("${spring.elasticsearch.rest.username}")
    private String userName;

//    @Value("${spring.elasticsearch.rest.password}")
    private String password;

    @Autowired
    private Environment environment;

    @Bean
    public RestHighLevelClient restHighLevelClient() {


        HttpHost[] httpHosts = new HttpHost[esUris.length];
        //将地址转换为http主机数组，未配置端口则采用默认9200端口，配置了端口则用配置的端口
        for (int i = 0; i < httpHosts.length; i++) {
            if (!StringUtils.isEmpty(esUris[i])) {
                if (esUris[i].contains(":")) {
                    String[] uris = esUris[i].split(":");
                    httpHosts[i] = new HttpHost(uris[0], Integer.parseInt(uris[1]), "http");              } else {
                    httpHosts[i] = new HttpHost(esUris[i], 9200, "http");
                }
            }
        }

        //判断，如果未配置用户名，则进行无用户名密码连接，配置了用户名，则进行用户名密码连接
        if (StringUtils.isEmpty(userName)) {
            RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHosts));
            return client;
        } else {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    //es账号密码
                    new UsernamePasswordCredentials(userName, password));
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(httpHosts)
                            .setHttpClientConfigCallback((httpClientBuilder) -> {
                                //这里可以设置一些参数，比如cookie存储、代理等等
                                httpClientBuilder.disableAuthCaching();
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            })
            );
            return client;
        }
    }
}
