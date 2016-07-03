package com.hack.art.repository;

import com.hack.art.criteria.Criteria;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 10.09.2015.
 */
public interface AbstractRepository<T> {

    Session getCurrentSession();

    void save(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(Long id) throws SQLException;

    void delete(T t) throws SQLException;

    void delete(Long id) throws SQLException;

    void update(T t) throws SQLException;

    int countAll() throws SQLException;

    List<T> getPaginated(Criteria criteria) throws SQLException;

    List<T> getWithCriteriaPaginated(Criteria criteria);

    Integer countWithCriteria(Criteria criteria);

}
