package com.example.dao.ExpertDAO;

import com.example.model.Expert;
import org.hibernate.query.Query;
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
        Query<Expert> query = (Query<Expert>) this.manager.createQuery("SELECT e FROM Expert e where e.name = :name");
        query.setParameter("name", name);
        Expert expert = query.uniqueResult();
        return expert;
    }

    @Override
    public Expert findExpertById(Long id) {
        Expert expert = manager.find(Expert.class,id);
        if (expert == null) {
            throw new EntityNotFoundException("Can't find Expert by ID "
                    + id);
        }
        return expert;
    }

    @Override
    public Expert updateExpert(Expert expert) {
      Expert updatedExpert = manager.find(Expert.class, expert.getId());
      updatedExpert.setName(expert.getName());
      updatedExpert.setMail(expert.getMail());
      updatedExpert.setSurname(expert.getSurname());
      updatedExpert.setPhone(expert.getPhone());
      manager.merge(updatedExpert);
      return updatedExpert;
    }


}
