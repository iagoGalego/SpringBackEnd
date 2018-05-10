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
        logger.info("Fetching all the Questions");
        List<Question> questions = questionsDAO.findAllQuestions();
        if (questions.isEmpty()) {
            return new ResponseEntity(new CustomErrorType("No questions found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @RequestMapping(value = "/{uri}", method = RequestMethod.GET)
    public ResponseEntity<?> getQuestion(@PathVariable("uri") String uri) {
        logger.info("Fetching Question with uri {}", uri);
        Question question = questionsDAO.findByURI(uri);
        if (question == null) {
            logger.error("Question with uri {} not found.", uri);
            return new ResponseEntity(new CustomErrorType("Question with uri " + uri
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createQuestion(@RequestBody Question question, @RequestParam(value = "questionnaire", required = false) String questionnaire, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Question : {}", question);

        question.genURI();
        if (questionsDAO.isQuestionExist(question)) {
            logger.error("Unable to create. A Question with uri '{}' already exist", question.getURI());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Question with uri '" +
                    question.getURI() + "' already exist."),HttpStatus.CONFLICT);
        }
        questionsDAO.insertQuestion(question);
        Questionnaire q = questionnairesDAO.findByURI(questionnaire);
        q.addQuestions(question);
        questionnairesDAO.updateQuestionnaire(q);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("questions/{id}").buildAndExpand(question.getURI()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{uri}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuestion(@PathVariable("uri") String uri, @RequestBody Question question, @RequestParam(value = "questionnaire", required = false) String questionnaire) {
        logger.info("Updating Question with uri {}", uri);

        Question currentQuestion = questionsDAO.findByURI(uri);

        if (currentQuestion == null) {
            logger.error("Unable to update. Question with uri {} not found.", uri);
            return new ResponseEntity(new CustomErrorType("Unable to update. Question with uri " + uri + " not found."),
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

    @RequestMapping(value = "/{uri}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestion(@PathVariable("uri") String uri, @RequestParam(value = "questionnaire", required = false) String questionnaire) {
        logger.info("Fetching & Deleting Question with uri {}", uri);

        Question question = questionsDAO.findByURI(uri);
        if (question == null) {
            logger.error("Unable to delete. Question with uri {} not found.", uri);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Question with uri " + uri + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        questionsDAO.deleteQuestionByURI(uri);
        Questionnaire q = questionnairesDAO.findByURI(questionnaire);
        q.removeQuestions(question);
        questionnairesDAO.updateQuestionnaire(q);

        return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Question> deleteAllQuestions() {
        logger.info("Deleting All the Questions");

        questionsDAO.deleteAllQuestions();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
