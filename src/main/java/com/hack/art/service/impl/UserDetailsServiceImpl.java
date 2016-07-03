package com.hack.art.service.impl;

import com.hack.art.domain.User;
import com.hack.art.dto.CurrentUser;
import com.hack.art.enumerate.RoleName;
import com.hack.art.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROLO on 25.11.2015.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public CurrentUser loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = new User();
        try {
            user = userRepository.getByLogin(login);
            logger.info("loadUserByUsername " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CurrentUser(user);
    }

//    private List<GrantedAuthority> getAuthirities(RoleName name) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        if (name.equals(RoleName.ADMIN)) {
//            authorities.add(new SimpleGrantedAuthority(RoleName.ADMIN.getRoleName()));
//        } else if (name.equals(RoleName.STUDENT)) {
//            authorities.add(new SimpleGrantedAuthority(RoleName.STUDENT.getRoleName()));
//        } else if (name.equals(RoleName.TEACHER)) {
//            authorities.add(new SimpleGrantedAuthority(RoleName.TEACHER.getRoleName()));
//        }
//
//        return authorities;
//
//    }
}
