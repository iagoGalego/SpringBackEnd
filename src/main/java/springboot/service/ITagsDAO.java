package springboot.service;

import es.usc.citius.hmb.games.Tag;

import java.util.List;

public interface ITagsDAO {
    List<Tag> findAllTags();
    Tag findByURI(String uri);
    List<Tag> findByValue(String value);
    Boolean isTagExist(Tag tag);
    void insertTag(Tag tag);
    Tag updateTag(Tag tag);
    void deleteTagByURI(String uri);
    void deleteAllTags();
}
