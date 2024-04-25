package com.example.se.service.impl;

import com.example.se.model.owners;
import com.example.se.repository.ownersRepository;
import com.example.se.service.ownersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ownersServiceImpl implements ownersService {

    private final ownersRepository ownersRepository;

    /**
     * Dependency Injection
     * @param ownersRepository: ownersRepository object DAO
     */
    @Autowired
    public ownersServiceImpl(ownersRepository ownersRepository) {
        this.ownersRepository = ownersRepository;
    }

    /**
     * Implement find by owner ID method
     * @param ownerID: int
     * @return
     * owners object
     */
    @Override
    public owners findByOwnerID(int ownerID) {
        return this.ownersRepository.findByOwnerID(ownerID);
    }

    /**
     * Implement find by username method
     * @param username: String
     * @return
     * owners object
     */
    @Override
    public owners findByUsername(String username) {
        return this.ownersRepository.findByUsername(username);
    }

    /**
     * Implement save method
     * @param owners owners object
     * @return
     * owners object which was saved
     */
    @Override
    public owners save(owners owners) {
        return this.ownersRepository.save(owners);
    }
}
