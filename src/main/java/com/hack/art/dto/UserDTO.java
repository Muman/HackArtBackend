package com.hack.art.dto;

import com.hack.art.domain.Role;
import com.hack.art.domain.User;
import com.hack.art.enumerate.Status;

import java.util.Date;

/**
 * Created by ROLO on 26.04.2016.
 */
public class UserDTO extends User{

    public UserDTO(Long id, String firstName, String lastName, String email, Date createDate, String login,
                   String password, Role role) {
        super(id, firstName, lastName, email, createDate, login, password, role);
    }


    public UserDTO(User user){
        super(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getCreateDate(),
                user.getLogin(), user.getPassword(), user.getRole());
    }

    public UserDTO() {
    }
}
