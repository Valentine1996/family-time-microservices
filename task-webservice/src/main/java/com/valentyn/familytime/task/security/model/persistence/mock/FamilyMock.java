/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-26-08 21:20:16 ::
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***

package com.valentyn.familytime.task.security.model.persistence.mock;

import com.valentyn.familytime.task.model.entity.Family;

/**
 * Mock for Family in persistence layout.
 *
 * @version 1.0
 * @see com.valentyn.familytime.task.model.entity.Role
 */
public class FamilyMock {
    /**
     * Mock for create.
     *
     * @return User.
     */
    public static Family create() {

        final Family family = new Family("test");
        family.setId(1L);

        return family;
    }
}
