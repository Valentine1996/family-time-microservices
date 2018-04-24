/**
 * Created by Andrii Gaidychuk on 25.07.2016.
 */

package com.valentyn.familytime.task.model.repository;

import com.valentyn.familytime.task.model.entity.BonusType;
import com.valentyn.familytime.task.model.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface BonusTypeRepository extends JpaRepository<BonusType, Long> {
    List<BonusType> findByFamily(Family family);
}
