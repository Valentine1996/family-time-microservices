/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-18-07 18:51:40 :: 2016-18-07 18:56:40
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.entity.User;

import java.util.List;

public interface UserService {

    //- SECTION :: MAIN -//

    /**
     * Find all users.
     *
     * @return List < User > List of users.
     */
    public List<User> findAll();

    /**
     * Find users for page.
     *
     * @param page Number of page for return.
     * @param limit Count items per page.
     *
     * @return List < User > List of users.
     */
    public List<User> findAll(int page, int limit);

    /**
     * Create a new user.
     *
     * @param user Data for creating new user
     * @return User Created
     */
    public User create(User user);

    /**
     * Find user by id.
     *
     * @param id Unique identificator
     * @return User Found
     */
    public User find(Long id);

    /**
     * Find all existed users by user's family.
     *
     * @param family - User's family.
     *
     * @return List < User > List of users.
     */
    public List<User> findByFamily(Family family);

    /**
     * Find user by username and Password.
     *
     * @param username Username
     * @return User Found
     */
    public User findByUsername(String username);

    /**
     * Update the existed user.
     *
     * @param user User for update
     * @return User Updated
     */
    public User update(User user);

    /**
     * Delete the existed user.
     *
     * @param id Identificator of user
     */
    public void delete(Long id);
}
