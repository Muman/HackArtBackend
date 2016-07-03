package com.hack.art.enumerate;

/**
 * Created by Roman on 16.09.2015.
 */
public enum Status {
    ACTIVE("ACTIVE"),
    BLOCKED("BLOCKED"),
    SUSPENDED("SUSPENDED");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
