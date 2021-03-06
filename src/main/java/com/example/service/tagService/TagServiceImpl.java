package com.example.service.tagService;
import com.example.dao.TagDAO.TagDAO;
import com.example.model.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService{

    private final TagDAO tagDAO;

    public TagServiceImpl(TagDAO tagDAO){
        this.tagDAO = tagDAO;
    }
    @Override
    public List<Tag> findAllTags() {
        List<Tag> tags = this.tagDAO.findAllFromRepository();
        return tags;
    }

    @Override
    public Tag findTagById(Long id) {
        Tag tag =  this.tagDAO.findTagById(id);
        return tag;
    }

    @Override
    public ResponseEntity<Void> deleteAllTags() {
        return this.tagDAO.deleteAllTags();
    }

    @Override
    public Tag createTag(Tag tag) {
        return this.tagDAO.createTag(tag);
    }

    @Override
    public Tag updateTag(Tag tag) {
        return this.tagDAO.updateTag(tag);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        return this.tagDAO.deleteTagById(id);
    }

}

