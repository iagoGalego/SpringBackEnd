package springboot.service;

import es.usc.citius.hmb.games.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("tagsDAO")
public class TagsDAO implements ITagsDAO {

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findByURI(String uri) {
        return tagRepository.findByURI(uri);
    }

    @Override
    public List<Tag> findByValue(String value){
        if(value.equals("*")) return tagRepository.findAll();
        else return tagRepository.getByValueRegexQuery(value);
    }

    @Override
    public Boolean isTagExist(Tag tag) {
        Tag q = tagRepository.findByURI(tag.getURI());
        return q != null;
    }

    @Override
    public void insertTag(Tag tag) {
        tagRepository.insert(tag);
    }

    @Override
    public Tag updateTag(Tag tag) {
        tagRepository.deleteByURI(tag.getURI());
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTagByURI(String uri) {
        tagRepository.deleteByURI(uri);
    }

    @Override
    public void deleteAllTags() {
        tagRepository.deleteAll();
    }

}

