package com.example.dao;

import com.example.model.Expert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ExpertDAO {

    List<Expert> findAllFromRepository();
    Expert postExpert(@PathVariable Expert expert);
    Expert findExpertByName(@PathVariable String name);
    Expert findExpertById(Long id);
    Expert updateExpert(Expert expert);
    ResponseEntity<Void> deleteExpert(Long id);
}
