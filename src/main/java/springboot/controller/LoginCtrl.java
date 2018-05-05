package springboot.controller;

import es.usc.citius.hmb.games.Question;
import es.usc.citius.hmb.games.Questionnaire;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springboot.model.User;
import springboot.service.IQuestionnairesDAO;
import springboot.service.IQuestionsDAO;
import springboot.util.CustomErrorType;

import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginCtrl {

    private static final Logger logger = LoggerFactory.getLogger(QuestionnairesCtrl.class);

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user) {
        logger.info("Login User : {}", user.getUser());

        String jwtToken = "";
        if (user.getUser() == null || user.getPass() == null) {
            return new ResponseEntity(new CustomErrorType(""),HttpStatus.BAD_REQUEST);
        }
        jwtToken = Jwts.builder().setSubject(user.getUser()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return new ResponseEntity<>(jwtToken, HttpStatus.CREATED);
    }
}