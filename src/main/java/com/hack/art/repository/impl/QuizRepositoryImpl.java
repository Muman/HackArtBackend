package com.hack.art.repository.impl;

import com.hack.art.domain.Quiz;
import com.hack.art.repository.QuizRepository;
import com.hack.art.util.LogUtil;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 07.12.2015.
 */
@Repository
@Transactional
public class QuizRepositoryImpl extends AbstractRepositoryImpl<Quiz> implements QuizRepository {

    QuizRepositoryImpl() {
        super(Quiz.class);
    }

    @Autowired
    private LogUtil log;

    public List<Quiz> getTestsByUser(Long id) throws SQLException {

        List<Quiz> quizs = getCurrentSession().createCriteria(Quiz.class)
                .add(Restrictions.eq("user.id", id))
                .list();
        return quizs;

    }

    public void saveTest(Quiz quiz) throws SQLException {
        getCurrentSession().save(quiz);
    }
}
