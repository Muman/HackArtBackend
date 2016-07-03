package com.hack.art.repository.impl;

import com.hack.art.domain.Role;
import com.hack.art.enumerate.RoleName;
import com.hack.art.repository.RoleRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by Roman on 16.09.2015.
 */
@Repository
@Transactional
public class RoleRepositoryImpl extends AbstractRepositoryImpl<Role> implements RoleRepository {

    RoleRepositoryImpl() {
        super(Role.class);
    }

    public Role getByName(RoleName name) {
        Role role = new Role();
        Criteria crit = getCurrentSession().createCriteria(Role.class);

        role = (Role) crit.add(Restrictions.eq("name", name)).uniqueResult();
        return role;
    }

    public void saveRole(Role role) throws SQLException {
        getCurrentSession().save(role);
    }

}
