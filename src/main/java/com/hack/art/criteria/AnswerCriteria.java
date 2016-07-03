package com.hack.art.criteria;

import com.hack.art.domain.Question;
import com.hack.art.domain.Answer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by ROLO on 14.12.2015.
 */
@Component
public class AnswerCriteria {

    private Question question;

    private Set<Answer> answers;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
