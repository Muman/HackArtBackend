package com.hack.art.dto;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Roman on 15.10.2015.
 */
@Component
public class PaginationData<T> {

    private Integer totalItems;

    private List<T> data;

    public PaginationData(){

    }

    public PaginationData(Integer totalItems, List<T> data){
        this.data = data;
        this.totalItems = totalItems;
    }
}
