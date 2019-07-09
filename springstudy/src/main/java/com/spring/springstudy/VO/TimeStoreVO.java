package com.spring.springstudy.VO;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author chenlilai
 * @title: TimeStoreVO
 * @projectName javaStudy1
 * @description:
 * @date 2019/7/415:18
 */
@Document(indexName = "timestore", type = "product")
public class TimeStoreVO {
    private Long userId;

    private Integer age;

    private  Integer sex;

    private String city;

    private GeoPoint location;

    private String distance;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
