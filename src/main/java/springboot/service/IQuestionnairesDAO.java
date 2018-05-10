package springboot.service;

import es.usc.citius.hmb.games.Question;
import es.usc.citius.hmb.games.Questionnaire;
import es.usc.citius.hmb.games.Tag;

import java.util.List;

public interface IQuestionnairesDAO {
    List<Questionnaire> findAllQuestionnaires();
    Questionnaire findByURI(String uri);
    List<Questionnaire> findByName(String name);
    List<Questionnaire> findByNameWithTags(String name, List<String> tags);
    List<Questionnaire> findByNameOrTag(String value);
    Boolean isQuestionnaireExist(Questionnaire questionnaire);
    void insertQuestionnaire(Questionnaire questionnaire);
    Questionnaire updateQuestionnaire(Questionnaire questionnaire);
    void deleteQuestionnaireByURI(String uri);
    void deleteAllQuestionnaires();
    void addQuestion(String uri, Question question);
}
