package springboot.service;

import es.usc.citius.hmb.games.Question;
import es.usc.citius.hmb.games.Questionnaire;
import es.usc.citius.hmb.games.Tag;
import es.usc.citius.hmb.model.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("questionnairesDAO")
public class QuestionnairesDAO implements IQuestionnairesDAO {

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Questionnaire> findAllQuestionnaires() {
        return questionnaireRepository.findAll();
    }

    @Override
    public Questionnaire findByURI(String uri) {
        return questionnaireRepository.findByURI(uri);
    }

    @Override
    public List<Questionnaire> findByName(String name){
        if(name.equals("*")) return questionnaireRepository.findAll();
        else return questionnaireRepository.getByNameRegexQuery(name);
    }

    @Override
    public List<Questionnaire> findByNameWithTags(String name, List<String> tags){
        List<Questionnaire> response, questionnaires;
        if(name == null) questionnaires = questionnaireRepository.findAll();
        else questionnaires = questionnaireRepository.getByNameRegexQuery(name);
        response = new ArrayList<>();
        Boolean isValid, condition;
        for(Questionnaire q : questionnaires){
            isValid = true;
            for(String t : tags){
                condition = false;
                for(Tag tg : q.getTags()){
                    if(tg.getValue().getStringValue().equals(t)){
                        condition = true;
                        break;
                    } else condition = false;
                }
                if(!condition) {
                    isValid = false;
                    break;
                } else isValid = true;
            }
            if(isValid) response.add(q);
        }
        return response;
    }

    @Override
    public Boolean isQuestionnaireExist(Questionnaire questionnaire) {
        Questionnaire q = questionnaireRepository.findByURI(questionnaire.getURI());
        return q != null;
    }

    public Boolean isTagExist(Tag tag) {
        Tag t = tagRepository.findByValue(tag.getValue().getStringValue());
        return t != null;
    }

    @Override
    public void insertQuestionnaire(Questionnaire questionnaire) {
        for(Tag tag : questionnaire.getTags()){
            if(!isTagExist(tag)) tagRepository.insert(tag);
        }
        questionnaireRepository.insert(questionnaire);
    }

    @Override
    public Questionnaire updateQuestionnaire(Questionnaire questionnaire) {
        for(Tag tag : questionnaire.getTags()){
            if(!isTagExist(tag)) tagRepository.insert(tag);
        }
        questionnaireRepository.deleteByURI(questionnaire.getURI());
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public void deleteQuestionnaireByURI(String uri) {
        questionnaireRepository.deleteByURI(uri);
    }

    @Override
    public void deleteAllQuestionnaires() {
        questionnaireRepository.deleteAll();
    }

    @Override
    public void addQuestion(String uri, Question question) {

    }
}
