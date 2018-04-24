/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-06-08 11:48 :: 2016-06-08 11:53
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.service.implementation;

import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.entity.TaskType;
import com.valentyn.familytime.task.model.repository.TaskTypeRepository;
import com.valentyn.familytime.task.model.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of TaskType service.
 *
 * @version 1.0
 */
@Service
public class TaskTypeServiceImpl implements TaskTypeService {

    @Autowired
    TaskTypeRepository taskTypeRepository;

    /**
     * Find parentTask type by ID
     *
     * @param id Identification
     * @return TaskType found.
     */
    @Override
    public TaskType findById(Long id) {
        return this.taskTypeRepository.findOne(id);
    }


    /**
     * Find all existed parentTask type.
     *
     * @return List < TaskType > List of parentTask types.
     */
    @Override
    public List<TaskType> findAll() {
        return this.taskTypeRepository.findAll();
    }

    /**
     * Find parentTask types for page.
     *
     * @param page  Number of page for return.
     * @param limit Count items per page.
     * @return List < TaskType > List of tasks.
     */
    @Override
    public List<TaskType> findAll(int page, int limit) {
        return taskTypeRepository.findAll(
                new PageRequest(
                        Math.max(page - 1, 0),
                        limit
                )
        )
                .getContent();
    }

    /**
     * Find all existed parentTask types by user's family.
     *
     * @param family - User's family.
     *
     * @return List < TaskType > List of parentTask types.
     */
    @Override
    public List<TaskType> findByFamily(Family family) {
        return this.taskTypeRepository.findByFamily(family);
    }

    /**
     * Create new parentTask type.
     *
     * @param taskType Data for creating new parentTask type
     * @return TaskType created
     */
    @Override
    public TaskType create(TaskType taskType) {
        return this.taskTypeRepository.save(taskType);
    }

    /**
     * Updated existed parentTask type.
     *
     * @param taskType Data for updated parentTask type
     * @return TaskType updated
     */
    @Override
    public TaskType update(TaskType taskType) {
        return this.taskTypeRepository.save(taskType);
    }

    /**
     * Delete existed parentTask type.
     *
     * @param id Identification of parentTask type
     */
    @Override
    public void delete(Long id) {
        this.taskTypeRepository.delete(id);
    }
}
