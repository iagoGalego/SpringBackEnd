package springboot.controller;

import es.usc.citius.hmb.games.Question;
import es.usc.citius.hmb.games.Questionnaire;
import springboot.service.IQuestionnairesDAO;
import springboot.service.IQuestionsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springboot.util.CustomErrorType;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsCtrl {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnairesCtrl.class);

    @Autowired
    IQuestionsDAO questionsDAO;
    @Autowired
    IQuestionnairesDAO questionnairesDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> listAllQuestions() {
        List<Question> questions = questionsDAO.findAllQuestions();
        if (questions.isEmpty()) {
            return new ResponseEntity(new CustomErrorType("No questions found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getQuestion(@PathVariable("id") String id) {
        logger.info("Fetching User with id {}", id);
        Question question = questionsDAO.findByURI(id);
        if (question == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createQuestion(@RequestBody Question question, @RequestParam(value = "questionnaire", required = false) String questionnaire, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", question);

        question.genURI();
        if (questionsDAO.isQuestionExist(question)) {
            logger.error("Unable to create. A User with name '{}' already exist", question.getStatement());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name '" +
                    question.getStatement() + "' already exist."),HttpStatus.CONFLICT);
        }
        questionsDAO.insertQuestion(question);
        Questionnaire q = questionnairesDAO.findByURI(questionnaire);
        q.addQuestions(question);
        questionnairesDAO.updateQuestionnaire(q);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(question.getURI()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuestion(@PathVariable("id") String id, @RequestBody Question question, @RequestParam(value = "questionnaire", required = false) String questionnaire) {
        logger.info("Updating User with id {}", id);

        Question currentQuestion = questionsDAO.findByURI(id);

        if (currentQuestion == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentQuestion.setStatement(question.getStatement());
        currentQuestion.setAnswerType(question.getAnswerType());
        currentQuestion.setQuestionType(question.getQuestionType());
        currentQuestion.setTags(question.getTags());

        questionsDAO.updateQuestion(currentQuestion);

        Questionnaire q = questionnairesDAO.findByURI(questionnaire);
        q.removeQuestions(question);
        q.addQuestions(question);
        questionnairesDAO.updateQuestionnaire(q);

        return new ResponseEntity<>(currentQuestion, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") String id, @RequestParam(value = "questionnaire", required = false) String questionnaire) {
        logger.info("Fetching & Deleting User with id {}", id);

        Question question = questionsDAO.findByURI(id);
        question.setURI(id);
        if (question == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        questionsDAO.deleteQuestionByURI(id);
        Questionnaire q = questionnairesDAO.findByURI(questionnaire);
        q.removeQuestions(question);
        questionnairesDAO.updateQuestionnaire(q);

        return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Question> deleteAllQuestions() {
        logger.info("Deleting All Users");

        questionsDAO.deleteAllQuestions();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
