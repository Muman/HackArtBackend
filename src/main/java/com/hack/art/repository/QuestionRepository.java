package com.hack.art.repository;

import com.hack.art.domain.CorrectAnswer;
import com.hack.art.domain.Question;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Roman on 06.10.2015.
 */
public interface QuestionRepository extends AbstractRepository<Question>{

    Set<Question> getAllQuestions() throws SQLException;

    Question getById(Long questionId) throws SQLException;

    void setCorrectAnswer(CorrectAnswer correctAnswer) throws SQLException;

    List<CorrectAnswer> getCorrectAnswersForQuestions(Set<Long> questionIds) throws SQLException;

    List<CorrectAnswer> getCAnswerByQuestion(Long questionId) throws SQLException;
}

