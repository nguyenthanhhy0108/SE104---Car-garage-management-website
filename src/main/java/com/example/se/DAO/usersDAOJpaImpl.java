package com.example.se.DAO;

import com.example.se.model.users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class usersDAOJpaImpl implements usersDAO{
    private final EntityManager entityManager;

    @Autowired
    public usersDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }
    @Override
    public List<users> findByUsername(String username) {
        try {
            TypedQuery<users> theQuery = entityManager.createQuery(
                    "FROM users u WHERE u.username = :username", users.class);
            theQuery.setParameter("username", username);
            List<users> usersList = theQuery.getResultList();
            return usersList;
        } catch (Exception exception) {
            return null;
        }
    }
}
