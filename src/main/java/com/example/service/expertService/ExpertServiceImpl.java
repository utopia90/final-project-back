package com.example.service.expertService;

import com.example.dao.ExpertDAO.ExpertDAO;
import com.example.model.Expert;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExpertServiceImpl implements ExpertService {
    private final ExpertDAO expertDAO;


    public ExpertServiceImpl(ExpertDAO expertDAO) {
        this.expertDAO = expertDAO;
    }


    @Override
    public List<Expert> findAllFromRepository() {
        List<Expert> experts = this.expertDAO.findAllFromRepository();
        return experts;
    }


    @Override
    public Expert findExpertByName(@PathVariable String name) {
        Expert expert =  this.expertDAO.findExpertByName(name);
        return expert;
    }

    @Override
    public Expert findExpertById(@PathVariable Long id) {
        Expert expert =  this.expertDAO.findExpertById(id);
        return expert;
    }

    @Override
    public Expert createExpert(Expert expert ) {
      return this.expertDAO.postExpert(expert);
    }

    @Override
    public Expert updateExpert( Expert expert) {
        return this.expertDAO.updateExpert(expert);
    }

}
