package com.example.se.service.impl;

import com.example.se.model.owners;
import com.example.se.repository.ownersRepository;
import com.example.se.service.ownersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     * Implement find by email
     * @param email: String
     * @return
     * owners object
     */
    @Override
    public owners findByOwnerEmail(String email) {
        return this.ownersRepository.findByOwnerEmail(email);
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

    @Transactional
    @Override
    public void deleteByOwnerID(int ownerID) {
        this.ownersRepository.deleteByOwnerID(ownerID);
    }

    /**
     * Implement find by owner phone number
     * @param phoneNumber: String
     * @return
     * owners object
     */
    @Override
    public owners findByOwnerPhoneNumber(String phoneNumber) {
        return this.ownersRepository.findByOwnerPhoneNumber(phoneNumber);
    }

    /**
     * Implement find all
     * @return
     * List of owner objects
     */
    @Override
    public List<owners> findAll() {
        return this.ownersRepository.findAll();
    }
}
