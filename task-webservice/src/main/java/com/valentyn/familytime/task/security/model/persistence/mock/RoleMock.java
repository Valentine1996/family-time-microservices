/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-25-08 20:30:16 ::
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***

package com.valentyn.familytime.task.security.model.persistence.mock;

import com.valentyn.familytime.task.model.entity.Role;

/**
 * Mock for Role in persistence layout.
 *
 * @version 1.0
 * @see Role
 */
public class RoleMock {

    /**
     * Mock for findByAuthority.
     *
     * @return Role.
     */
    public static Role findByAuthority() {

        final Role role = new Role(
                "PARENT"
        );
        role.setId(1L);

        return role;
    }
}
