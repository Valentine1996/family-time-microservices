/**
 * Created by Andrii Gaidychuk on 15.09.2016.
 */

package com.valentyn.familytime.task.model.service.implementation;

import com.valentyn.familytime.task.model.entity.Bonus;
import com.valentyn.familytime.task.model.entity.BonusType;
import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.repository.BonusRepository;
import com.valentyn.familytime.task.model.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of bonus service.
 *
 * @version 1.0
 */
@Service
public class BonusServiceImpl implements BonusService {

    @Autowired
    BonusRepository bonusRepository;

    /**
     * Find bonus by ID.
     *
     * @param id unique identificatos
     * @return Bonus found
     */
    @Override
    public Bonus findById(Long id) {
        return this.bonusRepository.findOne(id);
    }

    /**
     * Find all existed bonus.
     *
     * @return List < Bonus > found list bonuses
     */
    @Override
    public List<Bonus> findAll() {
        return this.bonusRepository.findAll();
    }

    /**
     * Find all existed bonus by user's family.
     *
     * @param family - User's family.
     * @return List < Bonus > list of the Bonuses by user's family.
     */
    @Override
    public List<Bonus> findByFamily(Family family) {
        return this.bonusRepository.findByFamily(family);
    }

    /**
     * Find all existed bonus by user's family and Bonus type.
     *
     * @param bonusType - Bonus type.
     * @return List < Bonus > list of the Bonuses by Bonus's type.
     */
    @Override
    public List<Bonus> findByBonusType(BonusType bonusType) {
        return this.bonusRepository.findByBonusType(bonusType);
    }

    /**
     * Create new bonus.
     *
     * @param bonus Bonus for create
     * @return Bonus created bonus
     */
    @Override
    public Bonus create(Bonus bonus) {
        return this.bonusRepository.save(bonus);
    }

    /**
     * Updated existed bonus.
     *
     * @param bonus Bonus for update
     * @return Bonus updated bonus
     */
    @Override
    public Bonus update(Bonus bonus) {
        return this.bonusRepository.save(bonus);
    }

    /**
     * Delete existed bonus.
     *
     * @param id unique identificator bonus
     */
    @Override
    public void delete(Long id) {
        this.bonusRepository.delete(id);
    }
}
