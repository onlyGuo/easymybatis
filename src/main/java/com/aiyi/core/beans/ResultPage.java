package com.aiyi.core.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2019-06-06 17:27
 * @Email 719348277@qq.com
 * @Description: 结果分页内容承载容器
 */
public class ResultPage<T> {

    private long totalSize;

    private int totalPageSize;

    private int page;

    private int length;

    private List<T> list = new ArrayList<>();

    public ResultPage(){
        super();
    }

    public ResultPage(long totalSize, int page, int length, List<T> list){
        this.totalSize = totalSize;
        this.page = page;
        this.length = length;
        this.totalPageSize = (int)(totalSize / length);
        if (totalSize % length != 0){
            totalPageSize ++;
        }
        this.list.addAll(list);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(int totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}