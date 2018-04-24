/**
 * Created by Andrii Gaidychuk on 15.09.2016.
 */

package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.Bonus;
import com.valentyn.familytime.task.model.entity.BonusType;
import com.valentyn.familytime.task.model.entity.Family;

import java.util.List;

public interface BonusService {

    //- SECTION :: MAIN -//

    /**
     * Find bonus by ID
     *
     * @return Bonus found.
     */
    public Bonus findById(Long id);

    /**
     * Find all existed bonus
     *
     * @return List < Bonus > List of bonuses.
     */
    public List<Bonus> findAll();

    /**
     * Find all existed bonus by user's family.
     *
     * @param family - User's family.
     * @return List < Bonus > List of bonuses by family.
     */
    public List<Bonus> findByFamily(Family family);

    /**
     * Find all existed bonus by Bonus's type.
     *
     * @param bonusType - Bonus type.
     * @return List < Bonus > List of bonuses by Bonus's type.
     */
    public List<Bonus> findByBonusType(BonusType bonusType);

    /**
     * Create new bonus.
     *
     * @param bonus Data for creating new bonus
     * @return Bonus created
     */
    public Bonus create(Bonus bonus);

    /**
     * Updated existed bonus.
     *
     * @param bonus Data for updated bonus
     * @return Bonus updated
     */
    public Bonus update(Bonus bonus);

    /**
     * Delete existed bonus.
     *
     * @param id identificator for delete bonus
     */
    public void delete(Long id);
}
