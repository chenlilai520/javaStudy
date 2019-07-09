package com.spring.springstudy.service;

import com.spring.springstudy.response.ResponseWrap;
import com.spring.springstudy.vo.TimeStoreVO;

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
    ResponseWrap insertUsert(TimeStoreVO user);

    /**
     * 查询所有数据
     * @return
     */
    ResponseWrap<List<TimeStoreVO>> selectAll();



    ResponseWrap queryNearby(double longitude,double latitude);


    ResponseWrap bulkIndex();
}
