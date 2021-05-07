package com.example.dao.TagDAO;

import com.example.model.Expert;
import com.example.model.Tag;
import org.hibernate.query.Query;
import org.springframework.http.ResponseEntity;
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

    @Override
    public ResponseEntity<Void> deleteAllTags() {
        Query query = (Query) manager.createQuery("DELETE FROM Tag e");
        query.executeUpdate();
        return ResponseEntity.noContent().build();
    }

    @Override
    public Tag createTag(Tag tag) {
        this.manager.persist(tag);
        return tag;
    }

    @Override
    public Tag updateTag(Tag tag) {
        Tag updatedTag= manager.find(Tag.class, tag.getId());
        updatedTag.setName(tag.getName());
        manager.merge(updatedTag);
        return updatedTag;
    }

    @Override
    public ResponseEntity<Void> deleteTagById(Long id) {
        Tag tag=this.manager.find(Tag.class,id);
        this.manager.remove(tag);
        return ResponseEntity.noContent().build();
        Tag tagToDelete = manager.find(Tag.class,id);
        List<Expert> experts = tagToDelete.getExperts();




        for(Expert expert: experts){
                if (expert.getTags().contains(tagToDelete)){
                    expert.getTags().remove(tagToDelete);

            }

        }
        return ResponseEntity.noContent().build();

    }
}