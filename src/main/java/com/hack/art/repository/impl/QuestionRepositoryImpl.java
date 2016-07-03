package com.hack.art.repository.impl;

import com.hack.art.domain.CorrectAnswer;
import com.hack.art.repository.QuestionRepository;
import com.hack.art.domain.Question;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Roman on 06.10.2015.
 */
@Repository
@Transactional
public class QuestionRepositoryImpl extends AbstractRepositoryImpl<Question> implements QuestionRepository {

    QuestionRepositoryImpl() {
        super(Question.class);
    }

    public Set<Question> getAllQuestions() throws SQLException {
        List<Question> questions = getCurrentSession().createCriteria(Question.class).list();
        questions.get(0);
        return new HashSet<Question>(questions);
    }

    public Question getById(Long questionId) {
        Question question = (Question) getCurrentSession().createCriteria(Question.class)
                .add(Restrictions.eq("id", questionId)).uniqueResult();
        return question;
    }

    public void setCorrectAnswer(CorrectAnswer correctAnswer) throws SQLException {
        getCurrentSession().save(correctAnswer);
    }

    public List<CorrectAnswer> getCorrectAnswersForQuestions(Set<Long> questionIds) throws SQLException {
        List<CorrectAnswer> answers = getCurrentSession().createCriteria(CorrectAnswer.class)
                .createAlias("question", "question").add(Restrictions.in("question.id", questionIds)).list();
        return answers;
    }

    public List<CorrectAnswer> getCAnswerByQuestion(Long questionId) throws SQLException {
        List<CorrectAnswer> answers = getCurrentSession().createCriteria(CorrectAnswer.class)
                .createAlias("question", "question").add(Restrictions.eq("question.id", questionId)).list();
        return null;
    }
}
