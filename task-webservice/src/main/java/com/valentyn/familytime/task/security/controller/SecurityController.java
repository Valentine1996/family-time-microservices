/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-25-08 20:14:16 ::
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.security.controller;

import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.entity.Role;
import com.valentyn.familytime.task.model.entity.User;
import com.valentyn.familytime.task.model.service.FamilyService;
import com.valentyn.familytime.task.model.service.RoleService;
import com.valentyn.familytime.task.model.service.SecurityService;
import com.valentyn.familytime.task.model.service.UserService;
import com.valentyn.familytime.task.security.model.entity.Roles;
import com.valentyn.familytime.task.security.view.form.InternalRegistrationForm;
import com.valentyn.familytime.task.security.view.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/security")
public class SecurityController {

    /// *** Properties  *** ///
    //- SECTION :: CRYPTOGRAPHY -//
    /**
     * Encoder for create hash of password.
     */

    //- SECTION :: SERVICES -//
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    SecurityService securityService;

    @Autowired
    FamilyService familyService;

    /// *** Methods     *** ///
    //- SECTION :: ACTIONS -//

    /**
     * Registration a new user.
     *
     * @param registrationForm  Data from registration form for register a new user.
     * @param response          Use for work with HTTP.
     *
     * @return User Created user.
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public User registrationAction(
            @RequestBody
            @Valid final RegistrationForm registrationForm,

            HttpServletResponse response
    ) {
        try {
            //Get role "parent"
            Role roleParent = roleService.findByAuthority(Roles.PARENT.name());

            if (roleParent == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            //Create list of roles
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleParent);

            //Create new user
            User newUser = new User(
                    new Family(registrationForm.getFamilyName()), //create new role
                    registrationForm.getFirstName(),
                    registrationForm.getLastName(),
                    registrationForm.getMiddleName(),
                    registrationForm.getUsername(),
                    registrationForm.getPassword(),
                    registrationForm.getBirthday(),
                    registrationForm.getGender(),
                    registrationForm.getLocale(),
                    true, // parent status
                    true, // active
                    userRoles
            );

            //- Success -//
            response.setStatus(HttpServletResponse.SC_CREATED);

            //- Persist -//
            return this.userService.create(newUser);
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }

        return null;
    }

    /**
     * Registration a new user by parent.
     *
     * @param internalRegistrationForm  Data from registration form for register a new user.
     * @param response          Use for work with HTTP.
     *
     * @return User Created user.
     */
    @RequestMapping(value = "/internal/signup", method = RequestMethod.POST)
    @ResponseBody
    public User internalRegistrationAction(
            @RequestBody
            @Valid final InternalRegistrationForm internalRegistrationForm,

            HttpServletResponse response
    ) {
        try {

            //Get role depends on the checkbox

            Role usersRole;


            if (internalRegistrationForm.getIsParent()) {
                usersRole = roleService.findByAuthority(Roles.PARENT.name());
            } else {
                usersRole = roleService.findByAuthority(Roles.CHILD.name());
            }

            if (usersRole == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }

            //Create list of roles
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(usersRole);

            //Create new user
            User newUser = new User(
                    securityService.getFamilyOfLoggedUser(), //create new role
                    internalRegistrationForm.getFirstName(),
                    internalRegistrationForm.getLastName(),
                    internalRegistrationForm.getMiddleName(),
                    internalRegistrationForm.getUsername(),
                    internalRegistrationForm.getPassword(),
                    internalRegistrationForm.getBirthday(),
                    internalRegistrationForm.getGender(),
                    internalRegistrationForm.getLocale(),
                    internalRegistrationForm.getIsParent(), // users status
                    true, // active
                    userRoles
            );

            //- Success -//
            response.setStatus(HttpServletResponse.SC_CREATED);

            //- Persist -//
            return this.userService.create(newUser);
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }

        return null;
    }

    /**
     * Get logged user.
     *
     * @param response Use for work with HTTP.
     * @return User - Logged user.
     */
    @RequestMapping(value = "/loggedUser", method = RequestMethod.GET)
    @ResponseBody
    public User internalRegistrationAction(
            HttpServletResponse response
    ) {
        response.setStatus(HttpServletResponse.SC_OK);

        User user = securityService.getLoggedUser();

        if (user != null) {
            return user;
        }
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        return null;
    }
}
