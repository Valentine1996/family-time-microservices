/**
 * Created by Andrii Gaidychuk on 24.09.2016.
 */

package com.valentyn.familytime.task.model.service.implementation;

import com.valentyn.familytime.task.model.entity.Task;
import com.valentyn.familytime.task.model.entity.TaskStatus;
import com.valentyn.familytime.task.model.repository.TaskRepository;
import com.valentyn.familytime.task.model.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of parentTask service.
 *
 * @version 1.0
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    /**
     * Find parentTask by ID.
     *
     * @param id unique identificatos
     * @return Task found
     */
    @Override
    public Task findById(Long id) {
        return this.taskRepository.findOne(id);
    }

    /**
     * Find all tasks.
     *
     * @return Lise < Task > list found tasks
     */
    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    /**
     * Find head tasks by performer and status.
     *
     * @param performerId Performer Id
     * @param taskStatus  task status
     * @return List < Task > List of tasks
     */
    @Override
    public List<Task> findHeadTasksByPerformerAndStatus(Long performerId, TaskStatus taskStatus) {
        return this.taskRepository.findHeadTasksByPerformerAndStatus(performerId, taskStatus);
    }

    /**
     * Find head tasks by creator and status.
     *
     * @param creatorId  Performer Id
     * @param taskStatus task status
     * @return List < Task > List of tasks
     */
    @Override
    public List<Task> findHeadTasksByCreatorAndStatus(Long creatorId, TaskStatus taskStatus) {
        return this.taskRepository.findHeadTasksByCreatorAndStatus(creatorId, taskStatus);
    }

    /**
     * Create parentTask.
     *
     * @param task Task for create
     * @return Task created parentTask
     */
    @Override
    public Task create(Task task) {
        return this.taskRepository.save(task);
    }

    /**
     * Update parentTask.
     *
     * @param task Task for update
     * @return Task updated parentTask
     */
    @Override
    public Task update(Task task) {
        return this.taskRepository.save(task);
    }

    /**
     * Delete existed parentTask.
     *
     * @param id unique identificator parentTask
     */
    @Override
    public void delete(Long id) {
        this.taskRepository.delete(id);
    }
}
