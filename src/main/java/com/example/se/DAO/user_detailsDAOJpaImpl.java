package com.example.se.DAO;

import com.example.se.model.user_details;
import com.example.se.model.users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class user_detailsDAOJpaImpl implements user_detailsDAO {
    private final EntityManager entityManager;
    @Autowired
    public user_detailsDAOJpaImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }
    @Override
    public List<user_details> findByEmail(String email) {
        try {
            TypedQuery<user_details> theQuery = entityManager.createQuery(
                    "FROM user_details ud WHERE ud.email = :email", user_details.class);
            theQuery.setParameter("email", email);
            return theQuery.getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public user_details save(user_details user_details) {
        try {
            user_details dbUserDetails = entityManager.merge(user_details);
            return dbUserDetails;
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }

}
