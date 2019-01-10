package com.yingside.bean;

import java.util.List;

public class QueryBean {
    //封装要查询的多个用户的id
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
