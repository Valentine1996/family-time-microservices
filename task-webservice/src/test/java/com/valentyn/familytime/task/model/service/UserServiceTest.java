/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2016 (c), by Valentine
 *
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 *
 * @date 2016-22-08 01:05:24 :: 2016-22-08 13:28:51
 *
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.Role;
import com.valentyn.familytime.task.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;
    
    @Before
    public void tearUp() throws Exception {
        super.tearUp();
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testSettingNewRoleForUser() {

        User firstUser = userService.find(1L);

        List<Role> currentRoles = firstUser.getRoles();
        currentRoles.add(roleService.find(1L));
        firstUser.setRoles(currentRoles);

        userService.update(firstUser);
    }

}
