/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-16-08 16:44:24 :: 2016-16-08 16:47:24
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.repository;

import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByFamily(Family family);
}