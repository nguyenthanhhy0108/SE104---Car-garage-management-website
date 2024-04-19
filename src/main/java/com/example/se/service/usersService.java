package com.example.se.service;

import com.example.se.model.users;

import java.util.List;

//Define methods for Service layer which is got by Controller
public interface usersService {

    /**
     * Define finByUsername method to search and return a list of authorities which have the similar username
     * @param username: Provided username
     * @return
     * List of users objects
     */
    List<users> findByUsername(String username);

    /**
     * Define save service to save a new users object to database
     * @param User: users object
     * @return
     * users object which is saved
     */
    users save(users User);

    /**
     * Service to change password of a username in database with a new password
     * @param username: Provided username (String)
     * @param newPassword: Raw pass (For instance: abc123!@)
     * @return
     * users object which was changed password
     */
    users updatePasswordByUsername(String username, String newPassword);

    /**
     * Define save service to delete an users object in database
     * @param users: users object
     */
    void delete(users users);

    /**
     * Service to check username exist in database
     * @param username: Provided username (String)
     * @return
     * A boolean
     */
    boolean checkUsernameExist(String username);
}
