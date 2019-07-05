package com.spring.springstudy.service;

import com.spring.springstudy.VO.UserVO;
import com.spring.springstudy.response.ResponseWrap;

import java.util.List;

/**
 * @author chenlilai
 * @title: UserService
 * @projectName javaStudy1
 * @description:
 * @date 2019/7/315:48
 */
public interface UserService {


    /**
     * 往ES添加数据
     * @param user
     * @return
     */
    ResponseWrap insertUsert(UserVO user);

    /**
     * 查询所有数据
     * @return
     */
    ResponseWrap<List<UserVO>> selectAll();



    ResponseWrap queryNearby(double longitude,double latitude);


    ResponseWrap bulkIndex();
}
