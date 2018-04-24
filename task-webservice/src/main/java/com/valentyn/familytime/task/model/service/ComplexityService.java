/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-06-08 12:32 :: 2016-06-08 12:37
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.Complexity;
import com.valentyn.familytime.task.model.entity.Family;

import java.util.List;

public interface ComplexityService {
    //- SECTION :: MAIN -//

    /**
     * Find complexity by ID.
     *
     * @return Complexity found
     */
    public Complexity findById(Long id);

    /**
     * Find all existed complexities.
     *
     * @return List < Complexity > List of complexities
     */
    public List<Complexity> findAll();

    /**
     * Find all existed complexities by user's family.
     *
     * @param family - User's family.
     *
     * @return List < Complexity > List of complexities.
     */
    public List<Complexity> findByFamily(Family family);

    /**
     * Create new complexity.
     *
     * @param complexity Data for creating new complexity
     * @return Complexity created
     */
    public Complexity create(Complexity complexity);

    /**
     * Updated existed complexity.
     *
     * @param complexity Data for updating complexity
     * @return Complexity updated
     */
    public Complexity update(Complexity complexity);

    /**
     * Delete existed complexity.
     *
     * @param id Identification of the complexity
     */
    public void delete(Long id);
}
