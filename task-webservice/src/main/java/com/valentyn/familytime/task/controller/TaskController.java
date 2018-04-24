package com.valentyn.familytime.task.controller;

import com.valentyn.familytime.task.model.entity.*;
import com.valentyn.familytime.task.model.service.*;
import com.valentyn.familytime.task.view.form.TaskForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for tasks.
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    /// *** Properties  *** ///

    /**
     * Service for work with Task.
     */
    @Autowired
    protected TaskService taskService;

    /**
     * Service for work with Bonus.
     */
    @Autowired
    protected BonusService bonusService;

    /**
     * Service for work with parentTask type.
     */
    @Autowired
    protected TaskTypeService taskTypeService;

    /**
     * Service for work with complexity.
     */
    @Autowired
    protected ComplexityService complexityService;

    /**
     * Service for getting data from logged user.
     */
    @Autowired
    protected SecurityService securityService;

    /**
     * Service for wor with user.
     */
    @Autowired
    protected UserService userService;

    /// ***   Methods   *** ///

    /**
     * Get parentTask by id.
     *
     * @param id       Id of Task.
     * @param response Use for work with HTTP.
     * @return Found Task.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Task findById(
            @PathVariable("id")
                    Long id,

            HttpServletResponse response
    ) {
        try {
            //- Search requested parentTask -//
            Task task = this.taskService.findById(id);
            //- Check if bonus was found -//
            notNull(task);
            return task;
        } catch (IllegalArgumentException e) {
            //- Error. Cannot find this bonus type -//
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return null;
    }

    /**
     * Get list of head tasks for logged person as performer.
     *
     * @return List of head tasks for logged person as performer.
     */
    @RequestMapping(value = "/headPerformerTasks", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Task> findTasksForLoggedPersonAsPerformerByStatus(
            @RequestParam
                    String status
    ) {
        try {
            //- Get logged person-//
            User loggedUser = securityService.getLoggedUser();

            TaskStatus taskStatus = TaskStatus.valueOf(status);

            //- Get tasks -/
            return this.taskService.findHeadTasksByPerformerAndStatus(
                    loggedUser.getId(), taskStatus);

        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    /**
     * Get list of head tasks for logged person as creator.
     *
     * @return List of head tasks for logged person as creator.
     */
    @RequestMapping(value = "/headCreatorTasks", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Task> findOpenTasksForLoggedPersonAsCreator(
            @RequestParam
                    String status
    ) {
        try {
            //- Get logged person-//
            User loggedUser = securityService.getLoggedUser();

            TaskStatus taskStatus = TaskStatus.valueOf(status);

            //- Get tasks -/
            return this.taskService.findHeadTasksByCreatorAndStatus(
                    loggedUser.getId(), taskStatus);

        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    /**
     * Create a new head task.
     *
     * @param taskForm Form with input.
     * @param response Use for work with HTTP.
     * @return Created parentTask.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Task createHeadTask(
            @RequestBody
            @Valid
                    TaskForm taskForm,

            HttpServletResponse response
    ) {
        try {
            //- Set HTTP status -//
            response.setStatus(HttpStatus.CREATED.value());

            //- Get bonus -/
            Bonus bonus = this.bonusService.findById(taskForm.getBonusId());

            //- Get complexity -/
            Complexity complexity = this.complexityService.findById(taskForm.getComplexityId());

            //- Get parentTask type -/
            TaskType taskType = this.taskTypeService.findById(taskForm.getTaskTypeId());

            //- Get logged person-//
            User loggedUser = securityService.getLoggedUser();

            //- Get performer-//
            User performer = this.userService.find(taskForm.getPerformerId());

            //Create new Task
            Task task = new Task();

            //Set Data
            task.setTaskType(taskType);
            task.setComplexity(complexity);
            task.setCreator(loggedUser);
            task.setPerformer(performer);
            task.setBonus(bonus);
            task.setHasSubtasks(false);
            task.setDescription(taskForm.getDescription());
            task.setPrize(taskForm.getPrize());
            task.setStatus(TaskStatus.OPEN);
            task.setCloseTo(taskForm.getCloseTo());
            //- Success. Return created task -//
            return this.taskService.create(task);
        } catch (DataIntegrityViolationException e) {
            //- Failure. Can not to create task -//
            response.setStatus(HttpStatus.CONFLICT.value());
        }

        return null;
    }

    /**
     * Update a head task.
     *
     * @param taskForm Form with input.
     * @param response Use for work with HTTP.
     * @return Updated parentTask.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Task updateHeadTask(
            @PathVariable("id")
                    Long id,

            @RequestBody
            @Valid
                    TaskForm taskForm,

            HttpServletResponse response
    ) {
        //- Search origin task -//
        Task taskOrigin = this.taskService.findById(id);

        if (taskOrigin == null) {
            //- Failure. Task not found -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        if (taskForm.getStatus() == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }

        //Update task
        try {
            //- Get bonus -/
            Bonus bonus = this.bonusService.findById(taskForm.getBonusId());

            //- Get complexity -/
            Complexity complexity = this.complexityService.findById(taskForm.getComplexityId());

            //- Get parentTask type -/
            TaskType taskType = this.taskTypeService.findById(taskForm.getTaskTypeId());

            //- Get logged person-//
            User loggedUser = securityService.getLoggedUser();

            //- Get performer-//
            User performer = this.userService.find(taskForm.getPerformerId());

            TaskStatus status = TaskStatus.valueOf(taskForm.getStatus());

            //Set new data
            taskOrigin.setTaskType(taskType);
            taskOrigin.setComplexity(complexity);
            taskOrigin.setCreator(loggedUser);
            taskOrigin.setPerformer(performer);
            taskOrigin.setBonus(bonus);
            taskOrigin.setHasSubtasks(false);
            taskOrigin.setDescription(taskForm.getDescription());
            taskOrigin.setPrize(taskForm.getPrize());
            taskOrigin.setStatus(status);
            taskOrigin.setCloseTo(taskForm.getCloseTo());

            //- Success. Return updated task -//
            return this.taskService.update(taskOrigin);
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            //- Failure. Can not to update task -//
            response.setStatus(HttpStatus.CONFLICT.value());
        }

        return null;
    }

    /**
     * Request for approval (change task status to pending).
     *
     * @param response Use for work with HTTP.
     * @return Updated task.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public Task changeTaskStatus(
            @PathVariable("id")
                    Long id,

            @RequestBody
                    String status,

            HttpServletResponse response
    ) {
        //- Search origin task -//
        Task task = this.taskService.findById(id);

        if (task == null) {
            //- Failure. Task not found -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        //Update task status
        try {

            TaskStatus taskStatus = TaskStatus.valueOf(status);

            task.setStatus(taskStatus);
            //- Success. Return updated task -//
            return this.taskService.update(task);
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            //- Failure. Can not to update task -//
            response.setStatus(HttpStatus.CONFLICT.value());
        }

        return null;
    }

    /**
     * Delete head task.
     *
     * @param id       Id of head task.
     * @param response Use for work with HTTP.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteAction(
            @PathVariable("id")
                    Long id,

            HttpServletResponse response
    ) {
        try {
            //- Try to delete parentTask -//
            this.taskService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            // Failure. Task doesn't exists
            //- Set HTTP status -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
