package com.example.service.expertService;

import com.example.model.Expert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ExpertService {
    List<Expert> findAllFromRepository();

    Expert findExpertByName(@PathVariable String name);


    Expert findExpertById(Long id);

    Expert createExpert(Expert expert);

    Expert updateExpert(Expert expert);

    ResponseEntity<Void> deleteById(Long id);

    ResponseEntity<Void> deleteAllExperts();
}
