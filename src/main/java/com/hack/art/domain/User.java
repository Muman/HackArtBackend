package com.hack.art.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hack.art.dto.UserDTO;
import com.hack.art.enumerate.Status;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roman on 10.09.2015.
 */
@Entity(name = "USERS")
@Table(name = "USERS")
public class User {

    public User(Long id, String firstName, String lastName, String email, Date createDate, String login,
                String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createDate = createDate;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(){

    }

    public User(UserDTO user){
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getCreateDate(),
                user.getLogin(), user.getPassword(), user.getRole());

    }


    @Id
    @GeneratedValue(generator = "ID_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ID_GENERATOR", sequenceName = "USERS_ID_SEQ")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "CREATE_DATE", nullable = true)
    private Date createDate;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = true)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

}
