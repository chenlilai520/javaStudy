package com.spring.springstudy.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Leo
 * @date 2017/2/21
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, MyInsertListMapper<T> {
}
