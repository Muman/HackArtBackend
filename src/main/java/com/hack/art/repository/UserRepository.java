package com.hack.art.repository;

import com.hack.art.domain.User;
import com.hack.art.enumerate.RoleName;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 10.09.2015.
 */

public interface UserRepository extends AbstractRepository<User>{

    List<User> getAllUsers() throws SQLException;

    User getByCredentials(String lastName, String firstName) throws SQLException;

    List<User> getByRoleName(RoleName roleNames) throws SQLException;

    User getByLogin(String login) throws SQLException;

    void updateUserPassword(Long userId, String password) throws SQLException;
}
