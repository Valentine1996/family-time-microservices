/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-07-10 12:35:15 :: 2016-07-10 12:45:05
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.Family;

import java.util.List;

public interface FamilyService {

    //- SECTION :: MAIN -//

    /**
     * Find all families
     *
     * @return List < Family > List of families.
     */
    public List<Family> findAll();

    /**
     * Find families for page.
     *
     * @param page Number of page for return.
     * @param limit Count items per page.
     *
     * @return List < Family > List of families.
     */
    public List<Family> findAll(int page, int limit);

    /**
     * Create a new family.
     *
     * @param family Data for creating new family
     * @return Family Created
     */
    public Family create(Family family);

    /**
     * Find family by id.
     *
     * @param id Unique identificator
     * @return Family Found
     */
    public Family find(Long id);

    /**
     * Update the existed family.
     *
     * @param family Family for update
     * @return Family Updated
     */
    public Family update(Family family);

    /**
     * Delete the existed family.
     *
     * @param id Identificator of family
     */
    public void delete(Long id);
}
