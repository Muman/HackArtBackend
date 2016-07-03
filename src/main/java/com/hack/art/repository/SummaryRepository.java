package com.hack.art.repository;

import com.hack.art.domain.User;
import com.hack.art.domain.Summary;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 17.01.2016.
 */
public interface SummaryRepository extends AbstractRepository<Summary>{

    List<Summary> getByUsers(List<User> students) throws SQLException;

    List<Summary> getByUser(Long userId) throws SQLException;
}
