package ru.mirea.pkmn.polupanpolina.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mirea.pkmn.polupanpolina.entity.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PkmnRepositoryImpl implements PkmnRepository {

    private final Logger logger;

    private final EntityManager em;

    @Autowired
    PkmnRepositoryImpl(EntityManager em, Logger logger) {

        this.logger = logger;
        this.em = em;
    }

    @Override
    public CardEntity getCard(String name) {
        try {
            return em.createQuery("SELECT c FROM CardEntity c WHERE c.name = :name", CardEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to find card by name: " + name, e);
        }
        return null;
    }

    @Override
    public CardEntity getCard(UUID uuid) {
        return em.find(CardEntity.class, uuid);
    }

    @Override
    public StudentEntity getStudent(String fullName) {
        String[] parts = fullName.split(" ");
        if (parts.length != 3) {
            logger.log(Level.WARNING, "Invalid student name format: " + fullName);
            return null;
        }
        try {
            return em.createQuery(
                            "SELECT s FROM StudentEntity s WHERE s.firstName = :firstName AND s.familyName = :familyName AND s.surName = :surName",
                            StudentEntity.class)
                    .setParameter("firstName", parts[0])
                    .setParameter("familyName", parts[1])
                    .setParameter("surName", parts[2])
                    .getSingleResult();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to find student: " + fullName, e);
        }
        return null;
    }

    @Override
    public StudentEntity getStudent(UUID uuid) {
        return em.find(StudentEntity.class, uuid);
    }

    @Override
    public void saveCard(CardEntity card) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(card);
            tx.commit();
            logger.log(Level.INFO, "Card saved: " + card.getName());
        } catch (Exception e) {
            tx.rollback();
            logger.log(Level.SEVERE, "Failed to save card: " + e.getMessage(), e);
        }
    }

    @Override
    public void saveStudent(StudentEntity student) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(student);
            tx.commit();
            logger.log(Level.INFO, "Student saved: " + student.getFirstName());
        } catch (Exception e) {
            tx.rollback();
            logger.log(Level.SEVERE, "Failed to save student: " + e.getMessage(), e);
        }
    }
}