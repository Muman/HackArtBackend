package com.hack.art.repository.impl;

import com.hack.art.domain.QuestionAnswer;
import com.hack.art.repository.AnswerRepository;
import com.hack.art.domain.Answer;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 23.11.2015.
 */
@Repository
@Transactional
public class AnswerRepositoryImpl extends AbstractRepositoryImpl<Answer> implements AnswerRepository {

    AnswerRepositoryImpl() {
        super(Answer.class);
    }

    public List<Answer> getAnswersByQuestion(Long questionId) throws SQLException {
        List<Answer> answers = getCurrentSession().createCriteria(Answer.class).createAlias("question", "q")
                .add(Restrictions.eq("q.id", questionId)).list();
        return answers;
    }

    public void saveAnswer(QuestionAnswer answer) throws SQLException {
        getCurrentSession().save(answer);
    }
}
