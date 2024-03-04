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
            return theQuery.getResultList();
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public users save(users User) {
        try {
            users dbUser = entityManager.merge(User);
            return dbUser;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
