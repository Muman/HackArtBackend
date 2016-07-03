package com.hack.art.repository;

import com.hack.art.domain.Role;
import com.hack.art.enumerate.RoleName;

import java.sql.SQLException;

/**
 * Created by Roman on 16.09.2015.
 */
public interface RoleRepository extends AbstractRepository<Role>{
    Role getByName(RoleName name);


    void saveRole(Role role) throws SQLException;
}
