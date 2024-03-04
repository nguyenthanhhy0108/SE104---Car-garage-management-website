package com.example.se.DAO;

import com.example.se.model.authorities;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class authoritiesDAOJpaImpl implements authoritiesDAO{
    private final EntityManager entityManager;
    @Autowired
    public authoritiesDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<authorities> findByUsername(String username) {
        try {
            TypedQuery<authorities> theQuery = entityManager
                    .createQuery("FROM authorities where username=:username", authorities.class);
            theQuery.setParameter("username", username);
            return theQuery.getResultList();
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public authorities save(authorities Authorities) {
        try {
            authorities dbAuthorities = entityManager.merge(Authorities);
            return dbAuthorities;
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
}
