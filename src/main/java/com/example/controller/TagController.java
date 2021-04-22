package com.example.controller;

import com.example.model.Expert;
import com.example.model.Tag;
import com.example.service.tagService.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TagController {

    private final TagService tagService;
    private final Logger log = LoggerFactory.getLogger(TagController.class);

    public TagController(TagService tagService){
        this.tagService = tagService;
    }


    @GetMapping("/tags")
    public List<Tag> findAll() {
        return tagService.findAllTags();
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> findTagById(@PathVariable("id") Long id) {
        log.info("REST request to find one tag by id:[]", id);
        Tag tagOpt = tagService.findTagById(id);
        if( tagOpt != null){
            return ResponseEntity.ok().body(tagOpt);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }}

    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) throws URISyntaxException {
        log.debug("REST request to save a tag: {}", tag);
        if (tag.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Tag result = tagService.createTag(tag);
        return ResponseEntity
                .created(new URI("/api/tags/" + result.getId())).body(result);

    }
    @PutMapping("/tags")
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag){
        log.debug("REST request to update a tag{}", tag);
        if (tag.getId() == null) {
            log.warn("updating tag without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Tag tagupdated = tagService.updateTag(tag);
        return ResponseEntity.ok().body(tagupdated);
    }
    @DeleteMapping("/tags")
    public ResponseEntity<Void> deleteAllTags(){
        log.debug("REST request to delete all tags");
        return tagService.deleteAllTags();
    }
    @DeleteMapping("/tags/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable Long id){
        log.debug("REST request to delete a tag by Id{} ", id);
        return tagService.deleteById(id);
    }
}
