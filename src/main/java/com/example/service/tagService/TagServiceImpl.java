package com.example.service.tagService;
import com.example.dao.TagDAO.TagDAO;
import com.example.model.Tag;

import java.util.List;

public class TagServiceImpl implements TagService{

    private final TagDAO tagDAO;


    public TagServiceImpl(TagDAO tagDAO) {
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
}
