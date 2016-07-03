package com.hack.art.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roman on 28.09.2015.
 */
@Entity(name = "SUMMARY")
public class Summary {

    @Id
    @GeneratedValue(generator = "summaryId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "summaryId", sequenceName = "SUMMARY_ID_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @Column(name = "DEGREE")
    private String degree;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "QUIZ_ID", referencedColumnName = "ID")
    private Quiz quiz;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
