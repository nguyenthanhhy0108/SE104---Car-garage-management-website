package com.example.se.DAO;

import com.example.se.model.userDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
            String sqlQuery = "SELECT ud.username, ud.email, ud.name, ud.nationality FROM userDetails ud WHERE ud.email = ?1";
            Query query = entityManager.createNativeQuery(sqlQuery, userDetails.class);
            query.setParameter(1, email);

            return query.getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
