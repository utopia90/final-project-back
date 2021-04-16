package com.example.controller;

import com.example.model.Expert;
import com.example.model.Tag;
import com.example.service.tagService.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TagController {

    private final TagService tagService;
    private final Logger log = LoggerFactory.getLogger(ExpertController.class);

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
}
