package com.example.se.service.impl;

import com.example.se.model.authorities;
import com.example.se.repository.authoritiesRepository;
import com.example.se.repository.usersRepository;
import com.example.se.model.users;
import com.example.se.service.usersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class usersServiceImpl implements usersService {

    //Define and initialize internal attribute (DAO layer)
    private final usersRepository usersRepository;
    private final authoritiesRepository authoritiesRepository;

    /**
     * Dependency Injection
     * @param usersRepository: usersRepository object
     * @param authoritiesRepository: authoritiesRepository object
     */
    @Autowired
    public usersServiceImpl(usersRepository usersRepository, authoritiesRepository authoritiesRepository) {
        this.usersRepository = usersRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    /**
     * Use DAO attribute to get list users from database
     * @param username: Provided username
     * @return
     * A list of users objects
     */
    @Override
    public List<users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    /**
     * Use DAO attribute to save users on database
     * @param User: users object
     * @return
     * users object which is saved
     */
    @Override
    public users save(users User) {
        return usersRepository.save(User);
    }

    /**
     * Use DAO attribute to save users on database with new password
     * @param username: Provided username (String)
     * @param newPassword: Raw pass (For instance: abc123!@)
     * @return
     * users object which was changed password
     */
    @Override
    public users updatePasswordByUsername(String username, String newPassword) {
        users new_user = usersRepository.findByUsername(username).get(0);
        new_user.setPassword(newPassword);
        usersRepository.save(new_user);
        return new_user;
    }

    /**
     * Service to delete object in database using DAO attributes
     * @param users: users object
     */
    @Override
    public void delete(users users) {
        List<authorities> temp = this.authoritiesRepository.findByUsername(users.getUsername());
        if(!temp.isEmpty()){
            this.authoritiesRepository.deleteAll(temp);
        }
        this.usersRepository.delete(users);
    }

    /**
     * Service to check username exist in database
     * @param username: Provided username (String)
     * @return
     * A boolean
     */
    @Override
    public boolean checkUsernameExist(String username) {
        if(usersRepository.findByUsername(username).isEmpty()){
            return false;
        }
        else return true;
    }
}
