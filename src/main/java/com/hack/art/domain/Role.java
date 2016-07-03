package com.hack.art.domain;

import com.hack.art.enumerate.RoleName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Roman on 16.09.2015.
 */
@Entity(name = "ROLE")
public class Role implements Serializable{

    @Id
    @SequenceGenerator(name = "ROLE_ID_GENERATOR", sequenceName = "ROLE_ID_SEQ")
    @GeneratedValue(generator = "ROLE_ID_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private RoleName name;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Role(RoleName roleName, Date createDate){
        this.name = roleName;
        this.createDate = createDate;
    }

    public Role(){

    }

}
