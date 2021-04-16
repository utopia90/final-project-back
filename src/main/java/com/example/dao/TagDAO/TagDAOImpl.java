package com.example.dao.TagDAO;

import com.example.model.Expert;
import com.example.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Tag> findAllFromRepository() {
        return this.manager.createQuery("SELECT e FROM Tag e").getResultList();
    }

    @Override
    public Tag findTagById(Long id) {
        Tag tag = manager.find(Tag.class,id);
        if (tag == null) {
            throw new EntityNotFoundException("Can't find tag by ID"
                    + id);
        }
        return tag;
    }
}
