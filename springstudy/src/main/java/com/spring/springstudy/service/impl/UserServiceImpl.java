package com.spring.springstudy.service.impl;

import com.spring.springstudy.VO.TimeStoreVO;
import com.spring.springstudy.response.ResponseWrap;
import com.spring.springstudy.service.UserService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @author chenlilai
 * @title: UserServiceImpl
 * @projectName javaStudy1
 * @description:
 * @date 2019/7/315:49
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TransportClient transportClient;

    @Override
    public ResponseWrap insertUsert(TimeStoreVO user) {
        double lat = 22.54605324;
        double lon = 114.02597315;
        TimeStoreVO timeStoreVO=new TimeStoreVO();
        timeStoreVO.setCity("湖南");
        timeStoreVO.setUserId(11112L);
        GeoPoint geoPoint =new GeoPoint(lat,lon);
        timeStoreVO.setLocation(geoPoint);

        IndexQuery indexQuery = new IndexQueryBuilder().withId(1213 + "")
                .withIndexName("timestore")
                .withType("product")
                .withObject(timeStoreVO).build();


//        IndexQuery indexQuery = new IndexQueryBuilder().withId(user.getAge() + "")
//                .withIndexName("my_test_index_004")
//                .withType("product")
//                .withObject(user).build();

        String index = elasticsearchTemplate.index(indexQuery);
        return ResponseWrap.success(index);
    }

    @Override
    public ResponseWrap<List<TimeStoreVO>> selectAll() {
        SortBuilder sortBuilder = SortBuilders.fieldSort("age")   //排序字段
                .order(SortOrder.DESC);   //排序方式

        String[] include = {"name", "age","city"}; //需要显示的字段

        FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(include, null);   //两个参数分别是要显示的和不显示的

        MatchQueryBuilder nameBuider =matchQuery("name","汤金浪");
        MatchQueryBuilder ageBuider =matchQuery("age","16");
        BoolQueryBuilder boolQueryBuilder=boolQuery().must(nameBuider).must(ageBuider);

        boolQueryBuilder.mustNot(termQuery("id",11112));

        SearchQuery searchQuery=  new NativeSearchQueryBuilder().withSourceFilter(fetchSourceFilter).
                withQuery(boolQueryBuilder).withPageable(PageRequest.of(0,5)).withSort(sortBuilder)
                //.withFilter(boolQuery().must(matchQuery("name","汤金浪")).filter(rangeQuery("age").gt(15))) // 这里是过滤条件
                .build();

         List<TimeStoreVO> list=elasticsearchTemplate.queryForList(searchQuery,TimeStoreVO.class);
         return ResponseWrap.success(list);
    }

    @Override
    public ResponseWrap queryNearby(double longitude, double latitude) {
        GeoPoint geoPoint=new GeoPoint(latitude,longitude);
        GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("location").point(geoPoint).distance(100, DistanceUnit.KILOMETERS); //distace 代表100千米  KILOMETERS 千米
//
        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort("location",geoPoint)
                .unit(DistanceUnit.KILOMETERS)
                .order(SortOrder.ASC);
//
//        Pageable pageable =PageRequest.of(0, 50);
//
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        boolQueryBuilder.mustNot(termQuery("city","湖"));
        boolQueryBuilder.filter(geoDistanceQueryBuilder);

//
//        NativeSearchQueryBuilder builder1 = new NativeSearchQueryBuilder().withQuery(geoDistanceQueryBuilder).withSort(sortBuilder).withPageable(pageable);
//
//        SearchQuery searchQuery = builder1.build();
//        List<TimeStoreVO> list =elasticsearchTemplate.queryForList(searchQuery,TimeStoreVO.class);



        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch("indextest").setTypes("product").setQuery(boolQueryBuilder).setFrom(0).setSize(50).addSort(sortBuilder);
        SearchHits hits = searchRequestBuilder.execute().actionGet().getHits();



//        return ResponseWrap.success(list);
        return null;
    }


    @Override
    public ResponseWrap bulkIndex() {
        List queries = new ArrayList();

        double lat = 39.929986;
        double lon = 116.395645;
        for(int i=140000;i<1300000;i++){
            double max = 0.00001;
            double min = 0.000001;
            Random random = new Random();
            double s = random.nextDouble() % (max - min + 1) + max;
            DecimalFormat df = new DecimalFormat("######0.000000");
            // System.out.println(s);
            String lons = df.format(s + lon);
            String lats = df.format(s + lat);
            Double dlon = Double.valueOf(lons);
            Double dlat = Double.valueOf(lats);

            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(i+"");
            TimeStoreVO timeStoreVO=new TimeStoreVO();
            timeStoreVO.setCity("深圳");
            timeStoreVO.setUserId(Long.valueOf(i));
            GeoPoint geoPoint =new GeoPoint(dlat,dlon);
            timeStoreVO.setLocation(geoPoint);
            indexQuery.setObject(timeStoreVO);
            indexQuery.setIndexName("timestore");
            indexQuery.setType("product");
            queries.add(indexQuery);
            System.out.println(i);
            if(i != 0 && i % 1000 == 0){
                elasticsearchTemplate.bulkIndex(queries);
            }
        }

        return ResponseWrap.success();

    }
}
