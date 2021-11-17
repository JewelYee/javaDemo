package com.yee.demo.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Desciption:
 * @Auther: yee
 * @Date:2020/10/13 4:06 PM
 */
@Component
public class EsDemo {

    static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("120.79.30.139", 9200, "http")));


    /**
     * 创建索引
     * @param index
     * @throws IOException
     */
    public static void createIndex(String index) throws Exception {
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("createIndex: " + JSON.toJSONString(createIndexResponse));
    }

    /**
     * 判断索引是否存在
     * @param index
     * @return
     * @throws IOException
     */
    public static boolean existsIndex(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println("existsIndex: " + exists);
        return exists;
    }
    /**
     * 增加记录
     * @param index
     * @param tests
     * @throws IOException
     */
    public void add(String index, Tests tests) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.source(JSON.toJSONString(tests), XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("add: " + JSON.toJSONString(indexResponse));
    }
    /**
     * 判断记录是都存在
     * @param index
     * @param tests
     * @return
     * @throws IOException
     */
    public boolean exists(String index, Tests tests) throws IOException {
        GetRequest getRequest = new GetRequest(index, tests.getId().toString());
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println("exists: " + exists);
        return exists;
    }

    /**
     * 获取记录信息
     * @param index
     * @throws IOException
     */
    public void get(String index) throws IOException {
        GetRequest getRequest = new GetRequest(index);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("get: " + JSON.toJSONString(getResponse));
    }

    /**
     * 更新记录信息
     * @param index
     * @param tests
     * @throws IOException
     */
    public void update(String index, Tests tests) throws IOException {
        tests.setName(tests.getName() + "updated");
        UpdateRequest request = new UpdateRequest();
        request.doc(request).doc(JSON.toJSONString(tests), XContentType.JSON);
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        System.out.println("update: " + JSON.toJSONString(updateResponse));
    }

    /**
     * 删除记录
     * @param index
     * @throws IOException
     */
    public void delete(String index) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index);
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println("delete: " + JSON.toJSONString(response));
    }

    /**
     * 搜索
     * @param index
     * @param type
     * @param name
     * @throws IOException
     */
    public void search(String index, String type, String name) throws IOException {
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(QueryBuilders.matchQuery("name", name)); // 这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
        // boolBuilder.must(QueryBuilders.matchQuery("id", tests.getId().toString()));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(100); // 获取记录数，默认10
        sourceBuilder.fetchSource(new String[] { "id", "name" }, new String[] {}); // 第一个是获取字段，第二个是过滤的字段，默认获取全部
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("search: " + JSON.toJSONString(response));
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            System.out.println("search -> " + hit.getSourceAsString());
        }
    }

    /**
     * 批量操作
     * @throws IOException
     */
    public void bulk() throws IOException {
//        // 批量增加
//        BulkRequest bulkAddRequest = new BulkRequest();
//        for (int i = 0; i < testsList.size(); i++) {
//            tests = testsList.get(i);
//            IndexRequest indexRequest = new IndexRequest(INDEX_TEST, TYPE_TEST, tests.getId().toString());
//            indexRequest.source(JSON.toJSONString(tests), XContentType.JSON);
//            bulkAddRequest.add(indexRequest);
//        }
//        BulkResponse bulkAddResponse = client.bulk(bulkAddRequest, RequestOptions.DEFAULT);
//        System.out.println("bulkAdd: " + JSON.toJSONString(bulkAddResponse));
//        search(INDEX_TEST, TYPE_TEST, "this");

        // 批量更新
//        BulkRequest bulkUpdateRequest = new BulkRequest();
//        for (int i = 0; i < testsList.size(); i++) {
//            tests = testsList.get(i);
//            tests.setName(tests.getName() + " updated");
//            UpdateRequest updateRequest = new UpdateRequest(INDEX_TEST, TYPE_TEST, tests.getId().toString());
//            updateRequest.doc(JSON.toJSONString(tests), XContentType.JSON);
//            bulkUpdateRequest.add(updateRequest);
//        }
//        BulkResponse bulkUpdateResponse = client.bulk(bulkUpdateRequest, RequestOptions.DEFAULT);
//        System.out.println("bulkUpdate: " + JSON.toJSONString(bulkUpdateResponse));
//        search(INDEX_TEST, TYPE_TEST, "updated");

        // 批量删除
        BulkRequest bulkDeleteRequest = new BulkRequest();
//        for (int i = 0; i < testsList.size(); i++) {
//            tests = testsList.get(i);
//            DeleteRequest deleteRequest = new DeleteRequest(INDEX_TEST, TYPE_TEST, tests.getId().toString());
//            bulkDeleteRequest.add(deleteRequest);
//        }
//        BulkResponse bulkDeleteResponse = client.bulk(bulkDeleteRequest, RequestOptions.DEFAULT);
//        System.out.println("bulkDelete: " + JSON.toJSONString(bulkDeleteResponse));
//        search(INDEX_TEST, TYPE_TEST, "this");
    }






    /**
     * @Description: 获取索引下的数据
     * @Param: [index, type, from, size]
     * @return: org.elasticsearch.action.search.SearchResponse
     * @Author: pengming.xu
     * @Date: 2019/3/27 15:57
     */
    public SearchResponse getAllIndexData(String index, int from, int size) throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(searchSourceBuilder);
        return client.search(request,RequestOptions.DEFAULT);
    }

    /**
     * @Description 多条件查询ES索引中的数据
     * @Param
     * @Return
     * @Date 2019-7-29 14:43
     * @Author xupm
     * @Since
     **/
    public SearchResponse searchLogs(String indexPattern, List<Map<String, String>> searchList, String dateField, int from, int size, String startTime, String endTime) throws IOException {
        SearchRequest request = new SearchRequest(indexPattern);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //若按时间进行排序，在查询没有时间字段的索引时会出错，暂时注销
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(dateField)){
            searchSourceBuilder.sort(new FieldSortBuilder(dateField).order(SortOrder.DESC));
            if(StringUtils.isEmpty(endTime)) {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(dateField).gte(startTime).format("yyyy-MM-dd HH:mm:ss"));
            }else {
                boolQueryBuilder.must(QueryBuilders.rangeQuery(dateField).gte(startTime).lte(endTime).format("yyyy-MM-dd HH:mm:ss"));
            }
        }
        BoolQueryBuilder innerBoolQueryBuilder = QueryBuilders.boolQuery();
        for (Map<String, String> searchMap:searchList) {
//            createQueryBuilder(boolQueryBuilder,innerBoolQueryBuilder,searchMap);
        }
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        request.source(searchSourceBuilder);
        return client.search(request,RequestOptions.DEFAULT);
    }

    public static void main(String[] args) throws Exception {
        String INDEX_TEST = "index_test"; // 索引名称
        List<Tests> testsList = null;
        testsList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Tests tests = new Tests();
            tests.setId(Long.valueOf(i));
            tests.setName("this is the test " + i);
            testsList.add(tests);
        }
        // 判断是否存在索引
        if (!existsIndex(INDEX_TEST)) {
            // 不存在则创建索引
            createIndex(INDEX_TEST);
            System.out.println("createIndex");
        }
        System.out.println("-------");
    }
}
