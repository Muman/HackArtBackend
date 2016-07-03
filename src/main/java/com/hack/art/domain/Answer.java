package com.hack.art.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roman on 28.09.2015.
 */
@Entity(name = "ANSWER")
public class Answer {

    @Id
    @GeneratedValue(generator = "answerId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "answerId", sequenceName = "ANSWER_ID_SEQ")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "QUESTION_ID")
//    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User creator;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Question getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(Question question) {
//        this.question = question;
//    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

}
