package com.hack.art.service;

import com.hack.art.domain.Role;
import com.hack.art.enumerate.RoleName;

import java.sql.SQLException;

/**
 * Created by Roman on 16.09.2015.
 */
public interface RoleService {
    
    void saveRole(Role role) throws SQLException;

    Role getRole(RoleName roleName) throws SQLException;
}
