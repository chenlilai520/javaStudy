package com.spring.springstudy.controller;

import com.spring.springstudy.response.ResponseWrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenlilai
 * @title: DemoController
 * @projectName javaStudy1
 * @description:
 * @date 2019/6/1116:18
 */
@Api(description = "swagger test")
@RestController
@RequestMapping("/api/demoTest")
public class DemoController {


    @PostMapping("/hello")
    @ApiOperation(value = "第一个swagge-API")
    public ResponseWrap getById(@ApiParam(value = "活动Id") @RequestParam(required = false) Integer id){

        return ResponseWrap.success(1);
    }
}
