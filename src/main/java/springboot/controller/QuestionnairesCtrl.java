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
import springboot.service.ITagsDAO;
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
    public ResponseEntity<List<Questionnaire>> listAllQuestionnaires(@RequestParam(value = "value", required = false) String value, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "tags", required = false) List<String> tags) {
        if(value != null){
            logger.info("Fetching Questionnaires with name or tags like '{}'", value);
            List<Questionnaire> questionnaires = questionnairesDAO.findByNameOrTag(value);
            if (questionnaires.isEmpty()) {
                logger.error("Questionnaires with name or tag '{}' not found.", value);
                return new ResponseEntity(new CustomErrorType("Questionnaires with name or tags " + value
                        + " not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questionnaires, HttpStatus.OK);
        }
        else if(name != null && tags != null){
            logger.info("Fetching Questionnaires with name '{}' and tags like '{}'", name, tags.toString());
            List<Questionnaire> questionnaires = questionnairesDAO.findByNameWithTags(name, tags);
            if (questionnaires.isEmpty()) {
                logger.error("Questionnaires with name '{}' and tags like '{}' not found", name, tags.toString());
                return new ResponseEntity(new CustomErrorType("Questionnaires with name '"+ name +
                        "' and tags like '"+tags.toString()+"' not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questionnaires, HttpStatus.OK);
        } else{
            logger.info("Fetching all the Questionnaires");
            List<Questionnaire> questionnaires = questionnairesDAO.findAllQuestionnaires();
            if (questionnaires.isEmpty()) {
                return new ResponseEntity(new CustomErrorType("No questionnaires found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questionnaires, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/{uri}", method = RequestMethod.GET)
    public ResponseEntity<?> getQuestionnaireByUri(@PathVariable("uri") String uri) {
        logger.info("Fetching Questionnaire with uri {}", uri);
        Questionnaire questionnaire = questionnairesDAO.findByURI(uri);

        if (questionnaire == null) {
            logger.error("Questionnaire with uri {} not found.", uri);
            return new ResponseEntity(new CustomErrorType("Questionnaire with uri " + uri
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questionnaire, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getQuestionnairesByName(@PathVariable("name") String name) {
        logger.info("Fetching Questionnaires with name like '{}'", name);
        List<Questionnaire> questionnaires = questionnairesDAO.findByName(name);
        if (questionnaires.isEmpty()) {
            logger.error("Questionnaires with name like '{}' not found.", name);
            return new ResponseEntity(new CustomErrorType("Questionnaires with name like '" + name
                    + "' not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questionnaires, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createQuestionnaire(@RequestBody Questionnaire questionnaire, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Questionnaire : {}", questionnaire.getURI());
        questionnaire.genURI();
        if (questionnairesDAO.isQuestionnaireExist(questionnaire)) {
            logger.error("Unable to create. A Questionnaire with uri {} already exist", questionnaire.getURI());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Questionnaire with uri " +
                    questionnaire.getURI() + " already exist."),HttpStatus.CONFLICT);
        }
        questionnairesDAO.insertQuestionnaire(questionnaire);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("questionnaires/{uri}").buildAndExpand(questionnaire.getURI()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{uri}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuestionnaire(@PathVariable("uri") String uri, @RequestBody Questionnaire questionnaire) {
        logger.info("Updating Questionnaire with uri {}", uri);

        Questionnaire currentQuestionnaire = questionnairesDAO.findByURI(uri);

        if (currentQuestionnaire == null) {
            logger.error("Unable to update. Questionnaire with uri {} not found.", uri);
            return new ResponseEntity(new CustomErrorType("Unable to update. Questionnaire with uri " + uri + " not found."),
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
        logger.info("Fetching & Deleting Questionnaire with uri {}", uri);

        Questionnaire questionnaire = questionnairesDAO.findByURI(uri);
        if (questionnaire == null) {
            logger.error("Unable to delete. Questionnaire with uri {} not found.", uri);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Questionnaire with uri " + uri + " not found."),
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
        logger.info("Deleting All Questionnaires");

        questionnairesDAO.deleteAllQuestionnaires();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{uri}", method = RequestMethod.POST)
    public ResponseEntity<?> addQuestion(@PathVariable("uri") String uri, @RequestBody Question question, UriComponentsBuilder ucBuilder) {
        logger.info("Adding question {} to questionnaire {}", question.getURI(), uri);

        questionnairesDAO.addQuestion(uri, question);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("questions/{uri}").buildAndExpand(question.getURI()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
