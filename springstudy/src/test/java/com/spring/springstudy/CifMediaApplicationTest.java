package com.spring.springstudy;

import com.spring.springstudy.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringstudyApplication.class)
@ActiveProfiles("test")
@Slf4j
public class CifMediaApplicationTest {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private IdWorker idWorker;

}
