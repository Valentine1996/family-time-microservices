/**
 * Created by Andrii Gaidychuk on 24.09.2016.
 */

package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.Task;
import com.valentyn.familytime.task.model.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    //- SECTION :: MAIN -//

    /**
     * Find Task by ID.
     *
     * @return Task found
     */
    public Task findById(Long id);

    /**
     * Find all existed tasks.
     *
     * @return List < Task > List of tasks
     */
    public List<Task> findAll();

    /**
     * Find head tasks by performer and status.
     *
     * @param performerId Performer Id
     * @param taskStatus  task status
     * @return List < Task > List of tasks
     */
    public List<Task> findHeadTasksByPerformerAndStatus(Long performerId, TaskStatus taskStatus);

    /**
     * Find head tasks by creator and status.
     *
     * @param creatorId  Performer Id
     * @param taskStatus task status
     * @return List < Task > List of tasks
     */
    public List<Task> findHeadTasksByCreatorAndStatus(Long creatorId, TaskStatus taskStatus);

    /**
     * Create new parentTask.
     *
     * @param task Data for creating new parentTask
     * @return Task created
     */
    public Task create(Task task);

    /**
     * Updated existed parentTask.
     *
     * @param task Data for updated parentTask
     * @return Task updated
     */
    public Task update(Task task);

    /**
     * Delete existed parentTask.
     *
     * @param id identificator for delete parentTask
     */
    public void delete(Long id);
}
