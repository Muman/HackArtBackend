package com.hack.art.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roman on 05.10.2015.
 */
@Entity(name = "QUESTION_ANSWER")
public class QuestionAnswer {

    @Id
    @GeneratedValue(generator = "questionAnswerId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "questionAnswerId", sequenceName = "question_answer_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID", referencedColumnName = "ID")
    private Answer answer;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "TIME_SPENT")
    private Double timeSpent;

    @Column(name = "TEST_ID")
    private Long testId;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Double getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Double timeSpent) {
        this.timeSpent = timeSpent;
    }

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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
}
