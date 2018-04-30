package springboot.service;

import java.util.List;

import es.usc.citius.hmb.games.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository. Query;

@SuppressWarnings("unchecked")
public interface QuestionRepository extends MongoRepository<Question, String> {

    List<Question> findAll();

    Question insert(Question question);

    @Query(value = "{ 'URI' : ?0 }")
    Question findByURI(String uri);

    void deleteAll();

    @Query(value = "{ 'URI' : ?0 }", delete = true)
    void deleteByURI(String uri);

    Question save(Question question);

}