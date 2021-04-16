package com.example.service.tagService;

import com.example.model.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();

    Tag findTagById(Long id);

    ResponseEntity<Void> deleteAllTags();
}
