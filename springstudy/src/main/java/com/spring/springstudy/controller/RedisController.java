package com.spring.springstudy.controller;

import com.spring.springstudy.response.ResponseWrap;
import com.spring.springstudy.service.RedisService;
import com.spring.springstudy.vo.TimeStoreVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenlilai
 * @title: RedisController
 * @projectName javaStudy1
 * @description:
 * @date 2019/7/1016:08
 */
@Api(description = "redis test")
@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;


    @PostMapping("/addredis")
    @ApiOperation(value = "添加数据")
    public ResponseWrap<List<TimeStoreVO>> addredis() {
        redisService.addRedis();
        return ResponseWrap.success();

    }
}
