package com.valentyn.familytime.task.controller;

import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.entity.TaskType;
import com.valentyn.familytime.task.model.service.SecurityService;
import com.valentyn.familytime.task.model.service.TaskTypeService;
import com.valentyn.familytime.task.view.form.TaskTypeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * Controller for parentTask types.
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/taskType")
public class TaskTypeController {
    /// *** Properties  *** ///
    /**
     * Service for work with parentTask types.
     */
    @Autowired
    protected TaskTypeService taskTypeService;

    /**
     * Service for getting data from logged user.
     */
    @Autowired
    protected SecurityService securityService;

    /// *** Methods     *** ///

    /**
     * Get list of parentTask types.
     *
     * @return List of parentTask types.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TaskType> findAll() {

        //- Get user's family -/
        Family usersFamily = securityService.getFamilyOfLoggedUser();

        return this.taskTypeService.findByFamily(usersFamily);
    }

    /**
     * Create a new parentTask type.
     *
     * @param taskTypeForm Form with input.
     * @param response     Use for work with HTTP.
     * @return Created parentTask type.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public TaskType createAction(
            @RequestBody
            @Valid
                    TaskTypeForm taskTypeForm,

            HttpServletResponse response
    ) {
        try {
            //- Set HTTP status -//
            response.setStatus(HttpStatus.CREATED.value());

            //- Get user's family -/
            Family usersFamily = securityService.getFamilyOfLoggedUser();

            if (usersFamily == null) {
                //- Failure. Can not to create parentTask type -//
                response.setStatus(HttpStatus.CONFLICT.value());
            } else {
                //Create new parentTask type
                TaskType taskType = new TaskType();
                //Set data
                //- Set user's family -//
                taskType.setFamily(usersFamily);
                taskType.setShortName(taskTypeForm.getShortName());
                taskType.setDescription(taskTypeForm.getDescription());

                //- Success. Return created parentTask type -//
                return this.taskTypeService.create(taskType);
            }
        } catch (DataIntegrityViolationException e) {
            //- Failure. Can not create parentTask type -//
            response.setStatus(HttpStatus.CONFLICT.value());
        }
        return null;
    }

    /**
     * Get parentTask type by id.
     *
     * @param id       Id of parentTask type.
     * @param response Use for work with HTTP.
     * @return Found parentTask type.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TaskType find(
            @PathVariable("id")
                    Long id,

            HttpServletResponse response
    ) {
        try {
            //- Search requested parentTask type -//
            TaskType taskType = this.taskTypeService.findById(id);

            //- Check if parentTask type was found -//
            notNull(taskType);

            return taskType;
        } catch (IllegalArgumentException e) {
            //- Error. Cannot find this parentTask type -//
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return null;
    }

    /**
     * Update already existed parentTask type.
     *
     * @param id           ID of parentTask type
     * @param taskTypeForm Updated data
     * @param response     Use for set HTTP status
     * @return Updated parentTask type.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public TaskType updateAction(
            @PathVariable("id")
                    Long id,

            @RequestBody
            @Valid
                    TaskTypeForm taskTypeForm,

            HttpServletResponse response
    ) {
        //- Search origin parentTask type -//
        TaskType taskTypeOrigin = this.taskTypeService.findById(id);

        if (taskTypeOrigin == null) {
            //- Failure. Task type not found -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        //- Update parentTask type -//
        try {
            //- Set new data -//
            taskTypeOrigin.setShortName(taskTypeForm.getShortName());
            taskTypeOrigin.setDescription(taskTypeForm.getDescription());

            //- Success. Return updated taskType -//
            return this.taskTypeService.update(taskTypeOrigin);
        } catch (DataIntegrityViolationException e) {
            //- Failure. Can not create parentTask type -//
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }

        return null;
    }

    /**
     * Delete parentTask type.
     *
     * @param id       Id of parentTask type.
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
            //- Try to delete parentTask type -//
            this.taskTypeService.delete(id);
        } catch (DataAccessException e) {
            // Failure. Task type doesn't exists
            //- Set HTTP status -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
