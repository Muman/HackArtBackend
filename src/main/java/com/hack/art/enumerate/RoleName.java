package com.hack.art.enumerate;

/**
 * Created by Roman on 16.09.2015.
 */
public enum RoleName {

    CLIENT("CLIENT"),
    ADMIN("ADMIN");

    private String roleName;

    RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName(){
        return roleName;
    }
}
