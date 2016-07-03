package com.hack.art.domain;

import com.hack.art.enumerate.QuestionType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Roman on 28.09.2015.
 */
@Entity(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(generator = "questionId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "questionId", sequenceName = "QUESTION_ID_SEQ")
    private Long id;

    @Column(name = "TYPE")
    private QuestionType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User creator;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TIME")
    private Double timeForAnswer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ANSWERS_FOR_QUESTION", joinColumns = @JoinColumn(name = "QUESTION_ID"),
    inverseJoinColumns = @JoinColumn(name = "ANSWER_ID"))
    private Set<Answer> answers;

    @Column(name = "SCORE")
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Double getTimeForAnswer() {
        return timeForAnswer;
    }

    public void setTimeForAnswer(Double timeForAnswer) {
        this.timeForAnswer = timeForAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

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
        this.name = name;
    }
}
