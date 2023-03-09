package cn.itcast.hotel;

import cn.itcast.hotel.constants.HotelConstants;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HotelIndexTest {
    private RestHighLevelClient client;

    @Test
    void testInit() {
        System.out.println(client);
    }

    @Test
    void testCreateHotelIndex() throws IOException {
        // 1.创建 Request 对象
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        // 2.请求参数，MAPPING_TEMPLATE 是DSL语句的静态常量
        request.source(HotelConstants.MAPPING_TEMPLATE, XContentType.JSON);
        // 3.发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    @BeforeEach
    void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://119.8.50.119:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
}
