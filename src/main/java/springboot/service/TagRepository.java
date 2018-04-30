package springboot.service;

import es.usc.citius.hmb.games.Tag;
import es.usc.citius.hmb.model.StringType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TagRepository extends MongoRepository<Tag, String> {

    List<Tag> findAll();

    @Query("{value.stringValue : {$regex : ?0}}")
    List<Tag> getByValueRegexQuery(String value);

    @Query(value = "{ 'URI' : ?0 }")
    Tag findByURI(String uri);

    @Query(value = "{ 'value.stringValue' : ?0 }")
    Tag findByValue(String value);

    Tag insert(Tag tag);

    Tag save(Tag tag);

    void deleteAll();

    void delete(Tag tag);

    @Query(value = "{ 'URI' : ?0 }", delete = true)
    void deleteByURI(String uri);

    void deleteByValue(StringType s);
}