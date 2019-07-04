package com.spring.springstudy.controller;

import com.spring.springstudy.VO.UserVO;
import com.spring.springstudy.response.ResponseWrap;
import com.spring.springstudy.service.UserService;
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
    @ApiOperation(value = "添加swagger数据")
    public ResponseWrap add(@RequestBody UserVO user){
        return         userService.insertUsert(user);
    }

    @PostMapping("/selectAll")
    @ApiOperation(value = "查询所有swagger数据")
    public ResponseWrap<List<UserVO>> selectAll(){
        return  userService.selectAll();
    }
}
