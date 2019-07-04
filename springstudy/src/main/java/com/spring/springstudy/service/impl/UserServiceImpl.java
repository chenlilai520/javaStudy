package com.spring.springstudy.service.impl;

import com.spring.springstudy.VO.UserVO;
import com.spring.springstudy.response.ResponseWrap;
import com.spring.springstudy.service.UserService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

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

    @Override
    public ResponseWrap insertUsert(UserVO user) {




        IndexQuery indexQuery = new IndexQueryBuilder().withId(user.getAge() + "")
                .withIndexName("my_test_index_004")
                .withType("product")
                .withObject(user).build();

        String index = elasticsearchTemplate.index(indexQuery);
        return ResponseWrap.success(index);
    }

    @Override
    public ResponseWrap<List<UserVO>> selectAll() {
        SortBuilder sortBuilder = SortBuilders.fieldSort("age")   //排序字段
                .order(SortOrder.DESC);   //排序方式

        String[] include = {"name", "age","city"}; //需要显示的字段

        FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(include, null);   //两个参数分别是要显示的和不显示的

        MatchQueryBuilder nameBuider =matchQuery("name","汤金浪");
        MatchQueryBuilder ageBuider =matchQuery("age","16");
        BoolQueryBuilder boolQueryBuilder=boolQuery().must(nameBuider).must(ageBuider);

        SearchQuery searchQuery=  new NativeSearchQueryBuilder().withSourceFilter(fetchSourceFilter).
                withQuery(boolQueryBuilder).withPageable(PageRequest.of(0,5)).withSort(sortBuilder)
                //.withFilter(boolQuery().must(matchQuery("name","汤金浪")).filter(rangeQuery("age").gt(15))) // 这里是过滤条件
                .build();

         List<UserVO> list=elasticsearchTemplate.queryForList(searchQuery,UserVO.class);
         return ResponseWrap.success(list);
    }
}
