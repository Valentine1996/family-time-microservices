/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-18-07 18:34:50 :: 2016-18-07 18:40:15
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service.implementation;

import com.valentyn.familytime.task.model.entity.Role;
import com.valentyn.familytime.task.model.repository.RoleRepository;
import com.valentyn.familytime.task.model.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of role service.
 *
 * @version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    /**
     * Find role for page.
     *
     * @return List < Role > List of roles.
     */
    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    /**
     * Create the new role.
     *
     * @param role Data for creating new role
     * @return Role Created role
     */
    @Override
    public Role create(Role role) {
        return this.roleRepository.save(role);
    }

    /**
     * Find role by id.
     *
     * @param id Unique identificator
     * @return Role Found
     */
    @Override
    public Role find(Long id) {
        return this.roleRepository.findOne(id);
    }

    /**
     * Find role by authority.
     *
     * @param authority Code of the role
     * @return Role Found
     */
    @Override
    public Role findByAuthority(String authority) {
        return this.roleRepository.findByAuthority(authority);
    }

    /**
     * Update the existed role.
     *
     * @param role Role for update
     * @return Role Updated role
     */
    @Override
    public Role update(Role role) {
        return this.roleRepository.save(role);
    }

    /**
     * Delete the existed role.
     *
     * @param id Identificator of role
     */
    @Override
    public void delete(Long id) {
        this.roleRepository.delete(id);
    }
}
