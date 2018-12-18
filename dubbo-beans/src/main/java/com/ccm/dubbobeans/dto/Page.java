package com.ccm.dubbobeans.dto;

import java.util.List;

/**
 * Created by chencm on 2018/11/16
 * 分页泛型类
 */
public class Page<T> {
    /**
     * 当前页码
     */
    private int currentPage=1;
    /**
     * 每页数量
     */
    private int pageSize=10;
    /**
     * 页数
     */
    private int pageCount;
    /**
     * 分页数据列表
     */
    private List<T> data;
    /**
     * 数据总量
     */
    private int total;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        if(this.pageSize<=0)
            return 0;
        return this.total%this.pageSize==0?this.total/this.pageSize:this.total/this.pageSize+1;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Page(){

    }

    public Page(int currentPage, int pageSize, List<T> data, int total){
        this.currentPage=currentPage;
        this.pageSize=pageSize;
        this.data=data;
        this.total=total;
    }
}
