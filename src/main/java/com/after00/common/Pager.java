package com.after00.common;

import java.io.Serializable;
import java.util.List;


/**
 * Created by panqq on 2017/4/4.
 * 分页辅助类
 */
public class Pager<T> implements Serializable {

    final Integer DEFAULT_PAGE_SIZE = 10;
    //已实际获取记录
    List<T> rows;
    //每页最大记录数 默认 10
    Integer pageSize = DEFAULT_PAGE_SIZE;
    //总记录数
    Integer total = 0;
    //当前页码
    Integer pageNumber;

    public Pager() {
    }

    public Pager(int pageSize, int pageNumber) {
        this.setPageSize(pageSize);
        this.setPageNumber(pageNumber);
    }

    //获取总页数
    public Integer getTotalPage() {
        int page = total / pageSize;
        if (total % pageSize > 0) {
            page++;
        }
        return page;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize <= 0 || pageSize > 200) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber <= 0) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
    }

    //获取起始记录位置下标
    public int getStartIndex() {
        if (pageSize == null || pageNumber == null) {
            return 1;
        } else {
            return pageSize * (pageNumber - 1);
        }
    }
}
