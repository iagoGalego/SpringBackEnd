package springboot.controller;

import es.usc.citius.hmb.games.Tag;
import es.usc.citius.hmb.model.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot.service.ITagsDAO;
import springboot.util.CustomErrorType;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsCtrl {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnairesCtrl.class);

    @Autowired
    ITagsDAO tagsDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> listAllTags(@RequestParam(value = "name", required = false) String name) {
        if(name == null){
            logger.info("Fetching all the Tags");
            List<Tag> tags = tagsDAO.findAllTags();
            if (tags.isEmpty()) {
                return new ResponseEntity(new CustomErrorType("No tags found"),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(tags, HttpStatus.OK);
        } else{
            logger.info("Fetching Tags with name like '{}'", name);
            List<Tag> tags = tagsDAO.findByValue(name);
            if (tags.isEmpty()) {
                logger.error("Tags with name like '{}' not found.", name);
                return new ResponseEntity(new CustomErrorType("Tags with name like '" + name
                        + "' not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(tags, HttpStatus.OK);
        }
    }
}
