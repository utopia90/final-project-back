package com.example.dao.ExpertDAO;

import com.example.model.Expert;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ExpertDAO {

    List<Expert> findAllFromRepository();
    Expert postExpert(@PathVariable Expert expert);
    Expert findExpertByName(@PathVariable String name);
    Expert findExpertById(Long id);
}
