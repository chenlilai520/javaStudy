package com.spring.springstudy.mapper;


import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 通用Mapper接口,特殊方法，批量插入，支持批量插入的数据库都可以使用，例如mysql,h2等
 *
 * @param <T> 不能为空
 * @author : zhongxianyin
 * @ClassName : com.cifmedia.dao.util.MyInsertListMapper
 * @E-mail : music120326@hotmail.com
 * @date : 2019/4/16 15:34
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface MyInsertListMapper<T> {

    /**
     * 批量插入，支持批量插入的数据库可以使用 自定义主键ID
     *
     * @param recordList
     * @return
     */
    @Options(useGeneratedKeys = true)
    @InsertProvider(type = MySpecialProvider.class, method = "dynamicSQL")
    int insertContainIdList(List<? extends T> recordList);

}