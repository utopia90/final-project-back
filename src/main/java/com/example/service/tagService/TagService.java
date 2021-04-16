package com.example.service.tagService;

import com.example.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();

    Tag findTagById(Long id);
}
