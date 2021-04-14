package com.example.service.expertService;

import com.example.model.Expert;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ExpertService {
    List<Expert> findAllFromRepository();

    Expert findExpertByName(@PathVariable String name);


    Expert findExpertById(Long id);

    Expert createExpert(Expert expert);
}
