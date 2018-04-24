/**
 * Created by Andrii Gaidychuk on 25.07.2016.
 */

package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.BonusType;
import com.valentyn.familytime.task.model.entity.Family;

import java.util.List;

public interface BonusTypeService {

    //- SECTION :: MAIN -//

    /**
     * Find bonus type by ID
     *
     * @param id unique identificatos
     * @return BonusType found.
     */
    public BonusType findById(Long id);

    /**
     * Find all existed bonus type
     *
     * @return List < BonusType > List of bonus types.
     */
    public List<BonusType> findAll();

    /**
     * Find all existed bonus types by user's family.
     *
     * @param family - User's family.
     * @return List < BonusType > List of bonus types.
     */
    public List<BonusType> findByFamily(Family family);

    /**
     * Create new bonus type.
     *
     * @param bonusType Data for creating new bonus type
     * @return BonusType created
     */
    public BonusType create(BonusType bonusType);

    /**
     * Updated existed bonus type.
     *
     * @param bonusType Data for updated bonus type
     * @return BonusType updated
     */
    public BonusType update(BonusType bonusType);

    /**
     * Delete existed bonus type.
     *
     * @param id identificator for delete bonus type
     */
    public void delete(Long id);
}
