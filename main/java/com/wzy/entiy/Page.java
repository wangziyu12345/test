package com.wzy.entiy;

import java.util.List;

/**
 * 作者：王子瑜
 * 时间：2020/10/16 14:44
 * 描述：
 */
public class Page<T> {

    private Long count;

    private List<T> data;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
