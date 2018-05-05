package springboot.service;

import es.usc.citius.hmb.games.Question;
import es.usc.citius.hmb.games.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("questionsDAO")
public class QuestionsDAO implements IQuestionsDAO {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question findByURI(String id) {
        return questionRepository.findByURI(id);
    }

    @Override
    public Boolean isQuestionExist(Question question) {
        Question q = questionRepository.findByURI(question.getURI());
        return q != null;
    }

    public Boolean isTagExist(Tag tag) {
        Tag t = tagRepository.findByValue(tag.getValue().getStringValue());
        return t != null;
    }

    @Override
    public void insertQuestion(Question question) {
        ArrayList<Tag> tags = new ArrayList<>();
        for(Tag tag : question.getTags()){
            if(!isTagExist(tag)) {
                tagRepository.insert(tag);
                tags.add(tag);
            }
            else tags.add(tagRepository.findByValue(tag.getValue().getStringValue()));
        }
        question.setTags(tags);
        questionRepository.insert(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        ArrayList<Tag> tags = new ArrayList<>();
        for(Tag tag : question.getTags()){
            if(!isTagExist(tag)) {
                tagRepository.insert(tag);
                tags.add(tag);
            }
            else tags.add(tagRepository.findByValue(tag.getValue().getStringValue()));
        }
        question.setTags(tags);
        questionRepository.deleteByURI(question.getURI());
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestionByURI(String id) {
        questionRepository.deleteByURI(id);
    }

    @Override
    public void deleteAllQuestions() {
        questionRepository.deleteAll();
    }
}
