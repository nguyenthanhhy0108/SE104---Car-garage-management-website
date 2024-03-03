package com.example.se.DAO;

import com.example.se.model.userDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class userDetailsDAOJpaImpl implements userDetailsDAO{
    private final EntityManager entityManager;
    @Autowired
    public userDetailsDAOJpaImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    @Override
    public List<userDetails> findByEmail(String email) {
        try {
            TypedQuery<userDetails> theQuery = entityManager.createQuery(
                    "FROM userDetails ud WHERE ud.email = :email", userDetails.class);
            theQuery.setParameter("email", email);
            return theQuery.getResultList();
        } catch (Exception exception) {
            return null;
        }
    }
}
