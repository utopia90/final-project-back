package com.example.dao.ExpertDAO;

import com.example.model.Expert;
import org.hibernate.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
    @Transactional
    public Expert postExpert(Expert expert) {
         this.manager.persist(expert);
         this.manager.flush();
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
      updatedExpert.setAvailability(expert.getAvailability());
      updatedExpert.setAddress(expert.getAddress());
      updatedExpert.setDni(expert.getDni());
      updatedExpert.setLinkedln(expert.getLinkedln());
      updatedExpert.setRating(expert.getRating());
      updatedExpert.setState(expert.getState());
      updatedExpert.setTags(expert.getTags());
      manager.merge(updatedExpert);
      return updatedExpert;
    }

    @Override
    public ResponseEntity<Void> deleteExpert(Long id) {
        Expert expert=this.manager.find(Expert.class,id);
        this.manager.remove(expert);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteAllExperts() {
        Query query = (Query) manager.createQuery("DELETE FROM Expert e");
        query.executeUpdate();
        return ResponseEntity.noContent().build();
    }
}
