/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-18-07 18:28:40 :: 2016-18-07 18:32:40
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.Role;

import java.util.List;

public interface RoleService {

    //- SECTION :: MAIN -//

    /**
     * Find roles for page
     *
     * @return List < Role > List of roles.
     */
    public List<Role> findAll();

    /**
     * Create a new role.
     *
     * @param role Data for creating new role
     * @return Role Created
     */
    public Role create(Role role);

    /**
     * Find role by id.
     *
     * @param id Unique identificator
     * @return Role Found
     */
    public Role find(Long id);

    /**
     * Find role by authority.
     *
     * @param authority Code of the role
     * @return Role Found
     */
    public Role findByAuthority(String authority);

    /**
     * Update the existed role.
     *
     * @param role Role for update
     * @return Role Updated
     */
    public Role update(Role role);

    /**
     * Delete the existed role.
     *
     * @param id Identificator of role
     */
    public void delete(Long id);
}
