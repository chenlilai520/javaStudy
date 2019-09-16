package com.spring.springstudy.service.impl;

import com.alibaba.fastjson.JSON;
import com.spring.springstudy.response.ResponseWrap;
import com.spring.springstudy.service.UserService;
import com.spring.springstudy.vo.TimeStoreVO;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
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

import java.math.BigDecimal;
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
        double lat = 22.5351131226;
        double lon = 114.0301036835;
        TimeStoreVO timeStoreVO=new TimeStoreVO();
        timeStoreVO.setCity("湖南");
        timeStoreVO.setUserId(user.getUserId());
        GeoPoint geoPoint =new GeoPoint(lat,lon);
        timeStoreVO.setLocation(geoPoint);
        timeStoreVO.setAge(22);
        timeStoreVO.setSex(1);

        IndexQuery indexQuery = new IndexQueryBuilder().withId(user.getUserId() + "")
                .withIndexName("timestore")
                .withType("product")
                .withObject(timeStoreVO).build();

        String index = elasticsearchTemplate.index(indexQuery);
        return ResponseWrap.success(index);
    }

    @Override
    public ResponseWrap<List<TimeStoreVO>> selectAll() {
        SortBuilder sortBuilder = SortBuilders.fieldSort("age")   //排序字段
                .order(SortOrder.DESC);   //排序方式

        String[] include = {"id", "age","city","sex","location"}; //需要显示的字段

        FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(include, null);   //两个参数分别是要显示的和不显示的

        MatchQueryBuilder nameBuider =matchQuery("name","汤金浪");
        MatchQueryBuilder ageBuider =matchQuery("age","16");
        BoolQueryBuilder boolQueryBuilder=boolQuery().mustNot(nameBuider).mustNot(ageBuider);


        SearchQuery searchQuery=  new NativeSearchQueryBuilder().withSourceFilter(fetchSourceFilter).withSort(sortBuilder).withPageable(PageRequest.of(0,10000))
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
//
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        boolQueryBuilder.must(termQuery("userId",140000));
        boolQueryBuilder.filter(geoDistanceQueryBuilder);

        List<TimeStoreVO>list=new ArrayList<>();

        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch("timestore").setTypes("product").setQuery(boolQueryBuilder).setFrom(0).setSize(50).addSort(sortBuilder);
        SearchHits hits = searchRequestBuilder.execute().actionGet().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            TimeStoreVO timeStoreVO = JSON.parseObject(sourceAsString, TimeStoreVO.class);
            BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[0]);

            if(geoDis.compareTo(BigDecimal.ONE)<0){
                 timeStoreVO.setDistance(geoDis.multiply(BigDecimal.valueOf(1000)).setScale(0, BigDecimal.ROUND_DOWN)+"m");
            }else if(geoDis.compareTo(BigDecimal.TEN)<0){
                timeStoreVO.setDistance(geoDis.setScale(0, BigDecimal.ROUND_DOWN)+"km");
            } else {
                timeStoreVO.setDistance(timeStoreVO.getCity());
            }
            list.add(timeStoreVO);
        }
        return ResponseWrap.success(list);
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
            timeStoreVO.setAge(i-139999);
            if(i%2==0){
                timeStoreVO.setSex(1);
            }else {
                timeStoreVO.setSex(2);
            }
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


    @Override
    public ResponseWrap timeStore() {

        SortBuilder sortBuilder = SortBuilders.fieldSort("age")   //排序字段
                .order(SortOrder.DESC);   //排序方式

        String[] include = { "age","city","headUrl","location","name"}; //需要显示的字段

        FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(include, null);   //两个参数分别是要显示的和不显示的


        SearchQuery searchQuery=  new NativeSearchQueryBuilder().withSourceFilter(fetchSourceFilter).withSort(sortBuilder).withPageable(PageRequest.of(0,10))
                .build();
        List<TimeStoreVO> list=elasticsearchTemplate.queryForList(searchQuery,TimeStoreVO.class);
        return ResponseWrap.success(list);
}
}
