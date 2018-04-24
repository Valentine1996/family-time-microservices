/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-18-07 19:02:50 :: 2016-18-07 19:05:15
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service.implementation;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.entity.User;
import com.valentyn.familytime.task.model.repository.UserRepository;
import com.valentyn.familytime.task.model.service.UserService;
import com.valentyn.familytime.task.notification.model.entity.Email;
import com.valentyn.familytime.task.notification.model.entity.EmailAddress;
import com.valentyn.familytime.task.notification.model.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * Implementation of user service.
 *
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value.
    private static int workload = 12;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationService notificationService;
    /**
     * Template manager.
     */
    @Autowired
    private Handlebars templateManager;
    /**
     * Service for getting message.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Method for crypting password.
     *
     * @param passwordPlainText
     *
     * @return Hashed password.
     */
    public static String hashPassword(String passwordPlainText) {
        String salt = BCrypt.gensalt(workload);
        String hashedPassword = BCrypt.hashpw(passwordPlainText, salt);

        return hashedPassword;
    }

    /**
     * Find all users .
     *
     * @return List < User > List of users.
     */
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    /**
     * Find users for page.
     *
     * @param page  Number of page for return.
     * @param limit Count items per page.
     * @return List < User > List of users.
     */
    @Override
    public List<User> findAll(int page, int limit) {
        return userRepository.findAll(
                new PageRequest(
                        Math.max(page - 1, 0),
                        limit
                )
        )
                .getContent();
    }

    /**
     * Create the new user.
     *
     * @param user Data for creating new user
     * @return Role Created user
     */

    @Override
    @Transactional()
    public User create(User user) {

        //Crypt password
        String cryptedPassword = hashPassword(user.getPassword());
        user.setPassword(cryptedPassword);

        //- Save user to persistence -//
        final User newUser = this.userRepository.save(user);

        //- Check created user -//
        notNull(newUser, "Cannot save user.");

        //- Send notification -//
        try {
            //- Prepare content -//
            Template template = this.templateManager.compile("signup");

            //FIXME: get email more safely
            //- Send notification-//
            this.notificationService.send(
                    new EmailAddress("iffamilytime@gmail.com"),
                    new EmailAddress(newUser.getUsername()),
                    new Email(
                            this.messageSource.getMessage(
                                    "notification.security.signup.subject",
                                    null,
                                    LocaleContextHolder.getLocale()
                            ),
                            template.apply(newUser)
                    )
            );
        } catch (IOException e) {
            //TODO add Logger
        }

        return newUser;
    }

    /**
     * Find user by id.
     *
     * @param id Unique identificator
     * @return User Found
     */
    @Override
    public User find(Long id) {
        return this.userRepository.findOne(id);
    }

    /**
     * Find all existed users by user's family.
     *
     * @param family - User's family.
     *
     * @return List < User > List of users.
     */
    @Override
    public List<User> findByFamily(Family family) {
        return this.userRepository.findByFamily(family);
    }

    /**
     * Find user by username.
     *
     * @param username Username
     * @return User Found
     */
    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    /**
     * Update the existed user.
     *
     * @param user User for update
     * @return User Updated user
     */
    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Delete the existed user.
     *
     * @param id Identificator of user
     */
    @Override
    public void delete(Long id) {
        this.userRepository.delete(id);
    }
}
