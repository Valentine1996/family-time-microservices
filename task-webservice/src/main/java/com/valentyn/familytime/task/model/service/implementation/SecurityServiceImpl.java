package com.valentyn.familytime.task.model.service.implementation;

import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.entity.User;
import com.valentyn.familytime.task.model.repository.UserRepository;
import com.valentyn.familytime.task.model.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Implementation of security service.
 *
 * @version 1.0
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    //-- PROPERTIES -//

    @Autowired
    UserRepository userRepository;

    //-- METHODS --//

    /**
     * Get family of Logged person.
     *
     * @return Family of logged user.
     */
    @Override
    public Family getFamilyOfLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username


        User loggedUsersData = userRepository.findByUsername(username);

        if (loggedUsersData != null && loggedUsersData.getFamily() != null) {
            return loggedUsersData.getFamily();
        }
        return null;
    }

    /**
     * Get logged user.
     *
     * @return User - logged user.
     */
    @Override
    public User getLoggedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username

        return this.userRepository.findByUsername(username);
    }
}
