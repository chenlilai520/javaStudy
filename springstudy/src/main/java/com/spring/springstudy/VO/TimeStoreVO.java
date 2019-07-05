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
    private Long id;

    private String name;

    private String city;

    private GeoPoint location;

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "TimeStoreVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", location=" + location +
                '}';
    }
}
