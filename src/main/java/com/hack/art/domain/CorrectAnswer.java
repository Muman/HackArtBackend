package com.hack.art.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roman on 05.10.2015.
 */
@Entity(name = "CORRECT_ANSWER")
public class CorrectAnswer {

    @Id
    @GeneratedValue(generator = "correctAnswerId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "correctAnswerId", sequenceName = "CORRECT_ANSWER_ID_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID", referencedColumnName = "ID")
    private Answer answer;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
