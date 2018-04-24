/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2017-01-20 14:32:33 ::
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.controller;

import com.valentyn.familytime.task.model.entity.BonusType;
import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.service.BonusTypeService;
import com.valentyn.familytime.task.model.service.SecurityService;
import com.valentyn.familytime.task.view.form.BonusTypeForm;
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
 * Controller for bonusTypes.
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/bonusType")
public class BonusTypeController {

    /// *** Properties  *** ///
    /**
     * Service for work with bonus types.
     */
    @Autowired
    protected BonusTypeService bonusTypeService;

    /**
     * Service for getting data from logged user.
     */
    @Autowired
    protected SecurityService securityService;

    /// *** Methods     *** ///

    /**
     * Get list of bonus types.
     *
     * @return List of bonus types.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BonusType> findAll() {

        //- Get user's family -/
        Family usersFamily = securityService.getFamilyOfLoggedUser();

        return this.bonusTypeService.findByFamily(usersFamily);
    }

    /**
     * Create a new bonus type.
     *
     * @param bonusTypeForm         Form with input.
     * @param response              Use for work with HTTP.
     *
     * @return Created bonusType.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public BonusType createAction(
            @RequestBody
            @Valid
                    BonusTypeForm bonusTypeForm,

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
                //Create new bonusType
                BonusType bonusType = new BonusType();
                //Set data
                //- Set user's family -//
                bonusType.setFamily(usersFamily);
                bonusType.setShortName(bonusTypeForm.getShortName());
                bonusType.setDescription(bonusTypeForm.getDescription());
                bonusType.setIconName(bonusTypeForm.getIconName());

                //- Success. Return created bonus type -//
                return this.bonusTypeService.create(bonusType);
            }
        } catch (DataIntegrityViolationException e) {
            //- Failure. Can not to create bonus type -//
            response.setStatus(HttpStatus.CONFLICT.value());
        }

        return null;
    }

    /**
     * Get bonusType by id.
     *
     * @param id          Id of bonusType.
     * @param response    Use for work with HTTP.
     *
     * @return Found style.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BonusType find(
            @PathVariable("id")
                    Long id,

            HttpServletResponse response
    ) {
        try {
            //- Search requested bonus type -//
            BonusType bonusType = this.bonusTypeService.findById(id);

            //- Check if bonus type was found -//
            notNull(bonusType);

            return bonusType;
        } catch (IllegalArgumentException e) {
            //- Error. Cannot find this bonus type -//
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return null;
    }

    /**
     * Update already exist bonus type.
     *
     * @param id        ID of bonusType
     * @param bonusTypeForm     Updated data
     * @param response  Use for set HTTP status
     *
     * @return Updated bonus type.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BonusType updateAction(
            @PathVariable("id")
                    Long id,

            @RequestBody
            @Valid
                    BonusTypeForm bonusTypeForm,

            HttpServletResponse response
    ) {
        //- Search origin bonus type -//
        BonusType bonusTypeOrigin = this.bonusTypeService.findById(id);

        if (bonusTypeOrigin == null) {
            //- Failure. Bonus type not found -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        //- Update bonus type -//
        try {
            //- Set new data -//
            bonusTypeOrigin.setShortName(bonusTypeForm.getShortName());
            bonusTypeOrigin.setDescription(bonusTypeForm.getDescription());

            //- TODO Add changing photo

            //- Success. Return updated bonus type -//
            return this.bonusTypeService.update(bonusTypeOrigin);
        } catch (DataIntegrityViolationException e) {
            //- Failure. Can not to create bonus type -//
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }

        return null;
    }

    /**
     * Delete bonus type.
     *
     * @param id          Id of bonusType.
     * @param response    Use for work with HTTP.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteAction(
            @PathVariable("id")
                    Long id,

            HttpServletResponse response
    ) {
        try {
            //- Try to delete bonus type -//
            this.bonusTypeService.delete(id);
        } catch (DataAccessException e) {
            // Failure. Bonus type doesn't exists
            //- Set HTTP status -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
