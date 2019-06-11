package com.spring.springstudy.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhongqiuwu
 * @desciption
 * @date 2019/04/20 11:11
 */
public class PageResponse<T> implements Serializable {
    @ApiModelProperty("当前页")
    private int pageNum;

    @ApiModelProperty("每页的数量")
    private int pageSize;

    @ApiModelProperty("当前页的数量")
    private int size;

    @ApiModelProperty("总页数")
    private int pages;

    @ApiModelProperty("下一页")
    private int nextPage;

    @ApiModelProperty("是否有下一页")
    private boolean hasNextPage = false;

    @ApiModelProperty("总记录数")
    private long total;

    @ApiModelProperty("结果集")
    private List<T> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", pages=" + pages +
                ", nextPage=" + nextPage +
                ", hasNextPage=" + hasNextPage +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
