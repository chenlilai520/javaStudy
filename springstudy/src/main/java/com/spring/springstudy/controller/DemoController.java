package com.spring.springstudy.controller;

import com.spring.springstudy.response.ResponseWrap;
import com.spring.springstudy.service.UserService;
import com.spring.springstudy.vo.TimeStoreVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ApiOperation(value = "添加Elasticsearch数据")
    public ResponseWrap add(@RequestBody TimeStoreVO user){
        return         userService.insertUsert(user);
    }

    @PostMapping("/selectAll")
    @ApiOperation(value = "查询所有Elasticsearch数据")
    public ResponseWrap<List<TimeStoreVO>> selectAll(){
        return  userService.selectAll();
    }

    @PostMapping("/bulkIndex")
    @ApiOperation(value = "批量添加Elasticsearch数据")
    public ResponseWrap<List<TimeStoreVO>> bulkIndex(){
        return  userService.bulkIndex();
    }

    @PostMapping("/queryNearby")
    @ApiOperation(value = "查询附近的人")
    public ResponseWrap<List<TimeStoreVO>> queryNearby(){
//        return  userService.queryNearby(114.0297496319,22.5356829253);
        return  userService.queryNearby(117.20217,40.736511);
    }


}
