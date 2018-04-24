package com.valentyn.familytime.task.controller;

import com.valentyn.familytime.task.model.entity.Complexity;
import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.service.ComplexityService;
import com.valentyn.familytime.task.model.service.SecurityService;
import com.valentyn.familytime.task.view.form.ComplexityForm;
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
 * Controller for complexities.
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/complexity")
public class ComplexityController {
    /// *** Properties  *** ///
    /**
     * Service for work with bonus types.
     */
    @Autowired
    protected ComplexityService complexityService;

    /**
     * Service for getting data from logged user.
     */
    @Autowired
    protected SecurityService securityService;

    /// *** Methods     *** ///

    /**
     * Get list of complexities.
     *
     * @return List of complexities.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Complexity> findAll() {

        //- Get user's family -/
        Family usersFamily = securityService.getFamilyOfLoggedUser();

        return this.complexityService.findByFamily(usersFamily);
    }

    /**
     * Create a new complexity.
     *
     * @param complexityForm Form with input.
     * @param response       Use for work with HTTP.
     * @return Created complexity.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Complexity createAction(
            @RequestBody
            @Valid
                    ComplexityForm complexityForm,

            HttpServletResponse response
    ) {
        try {
            //- Set HTTP status -//
            response.setStatus(HttpStatus.CREATED.value());

            //- Get user's family -/
            Family usersFamily = securityService.getFamilyOfLoggedUser();

            if (usersFamily == null) {
                //- Failure. Can not to create bonus type -//
                response.setStatus(HttpStatus.CONFLICT.value());
            } else {
                //Create new complexity
                Complexity complexity = new Complexity();
                //Set data
                //- Set user's family -//
                complexity.setFamily(usersFamily);
                complexity.setType(complexityForm.getType());
                complexity.setDescription(complexityForm.getDescription());

                //- Success. Return created bonus type -//
                return this.complexityService.create(complexity);
            }
        } catch (DataIntegrityViolationException e) {
            //- Failure. Can not to create bonus type -//
            response.setStatus(HttpStatus.CONFLICT.value());
        }

        return null;
    }

    /**
     * Get complexity by id.
     *
     * @param id       Id of complexity.
     * @param response Use for work with HTTP.
     * @return Found complexity.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Complexity find(
            @PathVariable("id")
                    Long id,

            HttpServletResponse response
    ) {
        try {
            //- Search requested complexity -//
            Complexity complexity = this.complexityService.findById(id);

            //- Check if complexity was found -//
            notNull(complexity);

            return complexity;
        } catch (IllegalArgumentException e) {
            //- Error. Cannot find this complexity -//
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return null;
    }

    /**
     * Update already existed complexity.
     *
     * @param id             ID of complexity
     * @param complexityForm Updated data
     * @param response       Use for set HTTP status
     * @return Updated complexity.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Complexity updateAction(
            @PathVariable("id")
                    Long id,

            @RequestBody
            @Valid
                    ComplexityForm complexityForm,

            HttpServletResponse response
    ) {
        //- Search origin complexity -//
        Complexity complexityOrigin = this.complexityService.findById(id);

        if (complexityOrigin == null) {
            //- Failure. Complexity not found -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        //- Update complexity -//
        try {
            //- Set new data -//
            complexityOrigin.setType(complexityForm.getType());
            complexityOrigin.setDescription(complexityForm.getDescription());

            //- Success. Return updated complexity -//
            return this.complexityService.update(complexityOrigin);
        } catch (DataIntegrityViolationException e) {
            //- Failure. Can not create complexity -//
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }

        return null;
    }

    /**
     * Delete complexity.
     *
     * @param id       Id of complexity.
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
            //- Try to delete complexity -//
            this.complexityService.delete(id);
        } catch (DataAccessException e) {
            // Failure. Complexity doesn't exists
            //- Set HTTP status -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
