package com.hack.art.criteria;

import com.hack.art.domain.Answer;
import com.hack.art.domain.Quiz;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ROLO on 13.12.2015.
 */
@Component
public class TestCriteria {

    private Quiz quiz;

    private List<Answer> answers;

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
