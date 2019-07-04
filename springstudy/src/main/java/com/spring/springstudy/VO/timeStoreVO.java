package com.spring.springstudy.VO;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author chenlilai
 * @title: timeStoreVO
 * @projectName javaStudy1
 * @description:
 * @date 2019/7/415:18
 */
@Document(indexName = "timestore", type = "product")
public class timeStoreVO {
    private Long id;

    private String name;

    private double latitude; //纬度

    private double longitude; //经度


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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "timeStoreVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
