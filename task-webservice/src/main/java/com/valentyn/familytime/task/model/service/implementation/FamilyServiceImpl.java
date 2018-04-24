/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-07-10 12:50:40 :: 2016-07-10 12:55:40
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service.implementation;

import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.repository.FamilyRepository;
import com.valentyn.familytime.task.model.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of family service.
 *
 * @version 1.0
 */
@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    FamilyRepository familyRepository;

    /**
     * Find families for page.
     *
     * @return List < Family > List of families.
     */
    @Override
    public List<Family> findAll() {
        return this.familyRepository.findAll();
    }

    /**
     * Find families for page.
     *
     * @param page  Number of page for return.
     * @param limit Count items per page.
     *
     * @return List < Family > List of families.
     */
    @Override
    public List<Family> findAll(int page, int limit) {
        return familyRepository.findAll(
                new PageRequest(
                        Math.max(page - 1, 0),
                        limit
                )
        )
                .getContent();
    }

    /**
     * Create the family.
     *
     * @param family Data for creating new family
     * @return Family Created family
     */
    @Override
    public Family create(Family family) {
        return this.familyRepository.save(family);
    }

    /**
     * Find family by id.
     *
     * @param id Unique identificator
     * @return Family Found
     */
    @Override
    public Family find(Long id) {
        return this.familyRepository.findOne(id);
    }

    /**
     * Update the existed family.
     *
     * @param family Family for update
     * @return Family Updated family
     */
    @Override
    public Family update(Family family) {
        return this.familyRepository.save(family);
    }

    /**
     * Delete the existed family.
     *
     * @param id Identificator of family
     */
    @Override
    public void delete(Long id) {
        this.familyRepository.delete(id);
    }
}
