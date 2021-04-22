package com.example.dao.TagDAO;

import com.example.model.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagDAO {
    List<Tag> findAllFromRepository();

    Tag findTagById(Long id);

    ResponseEntity<Void> deleteAllTags();

    Tag createTag(Tag tag);

    Tag updateTag(Tag tag);

}
