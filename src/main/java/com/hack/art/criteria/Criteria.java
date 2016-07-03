package com.hack.art.criteria;

/**
 * Created by Roman on 07.10.2015.
 */
public class Criteria {

    private int limit;

    private int offset;

    private String orderParam;

    private String orderWay;

    private String searchParam;

    private String searchValue;

    public String getOrderParam() {
        return orderParam;
    }

    public void setOrderParam(String orderParam) {
        this.orderParam = orderParam;
    }

    public String getOrderWay() {
        return orderWay;
    }

    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Criteria(){

    }

    public Criteria(int offset, int limit){
        this.offset = offset;
        this.limit = limit;
    }
}
