package springboot.service;

import java.util.List;

import es.usc.citius.hmb.games.Questionnaire;
import es.usc.citius.hmb.model.StringType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository. Query;

@SuppressWarnings("unchecked")
public interface QuestionnaireRepository extends MongoRepository<Questionnaire, String> {

    List<Questionnaire> findAll(Sort sort);

    @Query("{name.stringValue : {$regex : ?0}}")
    List<Questionnaire> getByNameRegexQuery(String name, Sort sort);

    @Query(value = "{ 'URI' : ?0 }")
    Questionnaire findByURI(String uri);

    Questionnaire insert(Questionnaire questionnaire);

    Questionnaire save(Questionnaire questionnaire);

    void deleteAll();

    void delete(Questionnaire questionnaire);

    @Query(value = "{ 'URI' : ?0 }", delete = true)
    void deleteByURI(String uri);

    void deleteByName(StringType s);
}