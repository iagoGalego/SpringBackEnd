package springboot.service;

import es.usc.citius.hmb.games.Question;
import es.usc.citius.hmb.games.Questionnaire;
import es.usc.citius.hmb.games.Tag;
import es.usc.citius.hmb.model.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("questionnairesDAO")
public class QuestionnairesDAO implements IQuestionnairesDAO {

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    TagRepository tagRepository;

    private Sort sort = new Sort(Sort.Direction.ASC, "name.stringValue");

    @Override
    public List<Questionnaire> findAllQuestionnaires() {
        return questionnaireRepository.findAll(sort);
    }

    @Override
    public Questionnaire findByURI(String uri) {
        return questionnaireRepository.findByURI(uri);
    }

    @Override
    public List<Questionnaire> findByName(String name){
        if(name.equals("*")) return questionnaireRepository.findAll(sort);
        else return questionnaireRepository.getByNameRegexQuery(name, sort);
    }

    @Override
    public List<Questionnaire> findByNameWithTags(String name, List<String> tags){
        List<Questionnaire> response, questionnaires;
        if(name == null) questionnaires = questionnaireRepository.findAll(sort);
        else questionnaires = questionnaireRepository.getByNameRegexQuery(name, sort);
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
    public List<Questionnaire> findByNameOrTag(String value){
        List<Questionnaire> response, questionnaires1, questionnaires2;
        questionnaires1 = questionnaireRepository.findAll(sort);
        questionnaires2 = questionnaireRepository.getByNameRegexQuery(value, sort);

        response = new ArrayList<>();
        for(Questionnaire q : questionnaires1){
            if(containsTag(q, value)) response.add(q);
        }

        for(Questionnaire q : questionnaires2){
            if(!containsQuestionnaire(response,q)) response.add(q);
        }

        return response;
    }

    private Boolean containsTag(Questionnaire q, String tag){
        for(Tag tg : q.getTags()){
            if(tg.getValue().getStringValue().equals(tag)) return true;
        }
        return false;
    }

    private Boolean containsQuestionnaire(List<Questionnaire> questionnaires, Questionnaire questionnaire){
        for(Questionnaire q : questionnaires){
            if(q.getURI().equals(questionnaire.getURI())) return true;
        }
        return false;
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
        ArrayList<Tag> tags = new ArrayList<>();
        for(Tag tag : questionnaire.getTags()){
            if(!isTagExist(tag)) {
                tagRepository.insert(tag);
                tags.add(tag);
            }
            else tags.add(tagRepository.findByValue(tag.getValue().getStringValue()));
        }
        questionnaire.setTags(tags);
        questionnaireRepository.insert(questionnaire);
    }

    @Override
    public Questionnaire updateQuestionnaire(Questionnaire questionnaire) {
        ArrayList<Tag> tags = new ArrayList<>();
        for(Tag tag : questionnaire.getTags()){
            if(!isTagExist(tag)) {
                tagRepository.insert(tag);
                tags.add(tag);
            }
            else tags.add(tagRepository.findByValue(tag.getValue().getStringValue()));
        }
        questionnaire.setTags(tags);
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
