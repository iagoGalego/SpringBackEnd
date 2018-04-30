package springboot.controller;

import java.util.ArrayList;
import java.util.List;

import es.usc.citius.hmb.games.Question;
import es.usc.citius.hmb.games.Questionnaire;
import es.usc.citius.hmb.games.Tag;
import es.usc.citius.hmb.model.StringType;
import springboot.service.IQuestionnairesDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springboot.service.IQuestionsDAO;
import springboot.service.QuestionnaireRepository;
import springboot.util.CustomErrorType;

@RestController
@RequestMapping("/questionnaires")
public class QuestionnairesCtrl {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnairesCtrl.class);

    @Autowired
    IQuestionnairesDAO questionnairesDAO;
    @Autowired
    IQuestionsDAO questionsDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Questionnaire>> listAllQuestionnaires(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "tags", required = false) List<String> tags) {
        if(name == null && tags == null){
            List<Questionnaire> questionnaires = questionnairesDAO.findAllQuestionnaires();
            if (questionnaires.isEmpty()) {
                return new ResponseEntity(new CustomErrorType("No questionnaires found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questionnaires, HttpStatus.OK);
        } else{
            logger.info("Fetching User with id {}", name);
            List<Questionnaire> questionnaires = questionnairesDAO.findByNameWithTags(name, tags);
            if (questionnaires.isEmpty()) {
                logger.error("User with id {} not found.", name);
                return new ResponseEntity(new CustomErrorType("User with id " + name
                        + " not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questionnaires, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/{uri}", method = RequestMethod.GET)
    public ResponseEntity<?> getQuestionnaireByUri(@PathVariable("uri") String uri) {
        logger.info("Fetching User with id {}", uri);
        Questionnaire questionnaire = questionnairesDAO.findByURI(uri);

        if (questionnaire == null) {
            logger.error("User with id {} not found.", uri);
            return new ResponseEntity(new CustomErrorType("User with id " + uri
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questionnaire, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getQuestionnairesByName(@PathVariable("name") String name) {
        logger.info("Fetching User with id {}", name);
        List<Questionnaire> questionnaires = questionnairesDAO.findByName(name);
        if (questionnaires.isEmpty()) {
            logger.error("User with id {} not found.", name);
            return new ResponseEntity(new CustomErrorType("User with id " + name
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questionnaires, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createQuestionnaire(@RequestBody Questionnaire questionnaire, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", questionnaire.getURI());
        questionnaire.genURI();
        if (questionnairesDAO.isQuestionnaireExist(questionnaire)) {
            logger.error("Unable to create. A User with uri {} already exist", questionnaire.getURI());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
                    questionnaire.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        questionnairesDAO.insertQuestionnaire(questionnaire);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{uri}").buildAndExpand(questionnaire.getURI()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{uri}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuestionnaire(@PathVariable("uri") String uri, @RequestBody Questionnaire questionnaire) {
        logger.info("Updating User with id {}", uri);

        Questionnaire currentQuestionnaire = questionnairesDAO.findByURI(uri);

        if (currentQuestionnaire == null) {
            logger.error("Unable to update. User with id {} not found.", uri);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + uri + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentQuestionnaire.setName(questionnaire.getName());
        currentQuestionnaire.setQuestions(questionnaire.getQuestions());
        currentQuestionnaire.setTags(questionnaire.getTags());

        questionnairesDAO.updateQuestionnaire(currentQuestionnaire);
        return new ResponseEntity<>(currentQuestionnaire, HttpStatus.OK);
    }

    @RequestMapping(value = "/{uri}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestionnaire(@PathVariable("uri") String uri) {
        logger.info("Fetching & Deleting User with id {}", uri);

        Questionnaire questionnaire = questionnairesDAO.findByURI(uri);
        if (questionnaire == null) {
            logger.error("Unable to delete. User with id {} not found.", uri);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + uri + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        for(Question question : questionnaire.getQuestions()){
            questionsDAO.deleteQuestionByURI(question.getURI());
        }
        questionnairesDAO.deleteQuestionnaireByURI(uri);
        return new ResponseEntity<Questionnaire>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Questionnaire> deleteAllQuestionnaires() {
        logger.info("Deleting All Users");

        questionnairesDAO.deleteAllQuestionnaires();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{uri}", method = RequestMethod.POST)
    public ResponseEntity<?> addQuestion(@PathVariable("uri") String uri, @RequestBody Question question, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", question);

        questionnairesDAO.addQuestion(uri, question);

        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/{uri}").buildAndExpand(questionnaire.getURI()).toUri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
