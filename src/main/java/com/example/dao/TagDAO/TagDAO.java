package com.example.dao.TagDAO;

import com.example.model.Tag;

import java.util.List;

public interface TagDAO {
    List<Tag> findAllFromRepository();

    Tag findTagById(Long id);
}
