package cn.itcast.hotel;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.impl.HotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class HotelDocumentTest {
    @Autowired
    private HotelService hotelService;

    private RestHighLevelClient client;

    @Test
    void testAddDocument() throws IOException {
        Hotel hotel = hotelService.getById(61083L);
        HotelDoc hotelDoc = new HotelDoc(hotel);

        IndexRequest request = new IndexRequest("hotel").id(hotel.getId().toString());
        request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
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