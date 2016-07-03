package com.hack.art.repository;

import com.hack.art.domain.Quiz;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 07.12.2015.
 */
public interface QuizRepository extends AbstractRepository<Quiz>{


    List<Quiz> getTestsByUser(Long id) throws SQLException;

    void saveTest(Quiz quiz) throws SQLException;
}
