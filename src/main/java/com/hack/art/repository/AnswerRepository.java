package com.hack.art.repository;

import com.hack.art.domain.QuestionAnswer;
import com.hack.art.domain.Answer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 23.11.2015.
 */
public interface AnswerRepository extends AbstractRepository<Answer>{

   List<Answer> getAnswersByQuestion(Long questionId) throws SQLException;

   void saveAnswer(QuestionAnswer answer) throws SQLException;
}
