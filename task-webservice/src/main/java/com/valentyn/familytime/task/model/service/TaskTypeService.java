/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-06-08 11:44 :: 2016-06-08 11:47
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.entity.TaskType;

import java.util.List;

public interface TaskTypeService {
    //- SECTION :: MAIN -//

    /**
     * Find parentTask type by ID
     * @return TaskType found.
     */
    public TaskType findById(Long id);

    /**
     * Find all existed parentTask type
     * @return List < TaskType > List of parentTask types.
     */
    public List<TaskType> findAll();

    /**
     * Find parentTask types for page.
     *
     * @param page Number of page for return.
     * @param limit Count items per page.
     *
     * @return List < TaskType > List of tasks.
     */
    public List<TaskType> findAll(int page, int limit);

    /**
     * Find all existed parentTask types by user's family.
     *
     * @param family - User's family.
     *
     * @return List < TaskType > List of parentTask types.
     */
    public List<TaskType> findByFamily(Family family);

    /**
     * Create new parentTask type.
     *
     * @param taskType Data for creating new parentTask type
     * @return TaskType created
     */
    public TaskType create(TaskType taskType);

    /**
     * Updated existed parentTask type.
     *
     * @param taskType Data for updated parentTask type
     * @return TaskType updated
     */
    public TaskType update(TaskType taskType);

    /**
     * Delete existed parentTask type.
     *
     * @param id Identificator of parentTask type
     */
    public void delete(Long id);
}
