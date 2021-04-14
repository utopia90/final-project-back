package com.example.dao.ExpertDAO;

import com.example.model.Expert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ExpertDAOImpl implements ExpertDAO {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Expert> findAllFromRepository() {
        return this.manager.createQuery("SELECT e FROM Expert e").getResultList();
    }

    @Override
    public Expert postExpert(Expert expert) {
         this.manager.persist(expert);
         return expert;
    }


    @Override
    public Expert findExpertByName(String name) {
        return null;
    }

    @Override
    public Expert findExpertById(Long id) {
        Expert expert = manager.find(Expert.class,id);
        if (expert == null) {
            throw new EntityNotFoundException("Can't find Expert for ID "
                    + id);
        }
        return expert;
    }


}
