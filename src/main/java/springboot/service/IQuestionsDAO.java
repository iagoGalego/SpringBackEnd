package springboot.service;

import es.usc.citius.hmb.games.Question;
import java.util.List;

public interface IQuestionsDAO {
    List<Question> findAllQuestions();
    Question findByURI(String id);
    Boolean isQuestionExist(Question question);
    void insertQuestion(Question question);
    Question updateQuestion(Question question);
    void deleteQuestionByURI(String id);
    void deleteAllQuestions();
}
