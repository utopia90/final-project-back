package com.example.controller;


import com.example.model.Expert;
import com.example.service.expertService.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin")
@RestController
@RequestMapping("/api")
public class ExpertController {

    private final ExpertService expertService;
    private final Logger log = LoggerFactory.getLogger(ExpertController.class);

    public ExpertController(ExpertService expertService){
        this.expertService = expertService;
    }

    @GetMapping("/experts")
    public List<Expert> findAll() {
        return expertService.findAllFromRepository();
    }
    @GetMapping("/experts/{id}")
    public ResponseEntity<Expert>  findExpertById(@PathVariable("id") Long id) {
        log.info("REST request to find one expert by id:[]", id);
        Expert expertOpt = expertService.findExpertById(id);
        if( expertOpt != null){
            return ResponseEntity.ok().body(expertOpt);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }}
    @GetMapping("/experts/name/{name}")
    public ResponseEntity<Expert>  findExpertByName(@PathVariable("name") String name) {
        log.info("REST request to find one expert by name:[]", name);
        Expert expertOpt = expertService.findExpertByName(name);
        if( expertOpt != null){
            return ResponseEntity.ok().body(expertOpt);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }}

    @PostMapping("/experts")
    public ResponseEntity<Expert> createExpert(@RequestBody Expert expert) throws URISyntaxException {
        log.debug("REST request to save a expert: {}", expert);
        if (expert.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Expert result = expertService.createExpert(expert);
        return ResponseEntity
                .created(new URI("/api/experts/" + result.getId())).body(result);

    }
    @PutMapping("/experts")
    public ResponseEntity<Expert> updateExpert(@RequestBody Expert expert){
        log.debug("REST request to update a expert{}", expert);
        if (expert.getId() == null) {
            log.warn("updating expert without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Expert expertDB = expertService.updateExpert(expert);
        return ResponseEntity.ok().body(expertDB);
    }
    @DeleteMapping("/experts/{id}")
    public ResponseEntity<Void> deleteExpertById(@PathVariable Long id){
        log.debug("REST request to delete a expert by Id{} ", id);
        return expertService.deleteById(id);
    }
    @DeleteMapping("/experts")
    public ResponseEntity<Void> deleteAllExperts(){
        log.debug("REST request to delete all experts");
        return expertService.deleteAllExperts();
    }
}
