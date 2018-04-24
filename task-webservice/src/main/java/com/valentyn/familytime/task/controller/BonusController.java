/**
 * Created by Andrii Gaidychuk on 15.09.2016.
 */

package com.valentyn.familytime.task.controller;

import com.valentyn.familytime.task.model.entity.Bonus;
import com.valentyn.familytime.task.model.entity.BonusType;
import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.service.BonusService;
import com.valentyn.familytime.task.model.service.BonusTypeService;
import com.valentyn.familytime.task.model.service.SecurityService;
import com.valentyn.familytime.task.view.form.BonusForm;
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
 * Controller for bonus.
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/bonus")
public class BonusController {
    /// *** Properties  *** ///

    /**
     * Service for work with Bonus.
     */
    @Autowired
    protected BonusService bonusService;

    /**
     * Service for work with BonusType.
     */
    @Autowired
    protected BonusTypeService bonusTypeService;

    /**
     * Service for getting data from logged user.
     */
    @Autowired
    protected SecurityService securityService;

    /// ***   Methods   *** ///

    /**
     * Get bonus by id.
     *
     * @param id       Id of Bonus.
     * @param response Use for work with HTTP.
     * @return Found Bonus.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Bonus findById(
            @PathVariable("id")
                    Long id,

            HttpServletResponse response
    ) {
        try {
            //- Search requested bonus -//
            Bonus bonus = this.bonusService.findById(id);
            //- Check if bonus was found -//
            notNull(bonus);
            return bonus;
        } catch (IllegalArgumentException e) {
            //- Error. Cannot find this bonus type -//
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return null;
    }

    /**
     * Get list of bonuses by Family.
     *
     * @return List of bonuses by Family.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Bonus> findByFamily() {

        //- Get user's family -/
        Family usersFamily = securityService.getFamilyOfLoggedUser();
        return this.bonusService.findByFamily(usersFamily);
    }

    /**
     * Get list of bonuses by Bonus type.
     *
     * @return List of bonuses by Bonus type.
     */
    @RequestMapping(value = "/bonusType", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Bonus> findByBonusType(
            @RequestParam
                    Long bonusTypeId
    ) {
        try {
            if (bonusTypeId == null) {
                throw new DataIntegrityViolationException("Can not get data of the bonus");
            } else {
                //- Get bonus type and return List <Bonus> -/
                return this.bonusService.findByBonusType(
                        this.bonusTypeService.findById(bonusTypeId));
            }
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    /**
     * Create a new bonus.
     *
     * @param bonusForm Form with input.
     * @param response  Use for work with HTTP.
     * @return Created bonus.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Bonus createAction(
            @RequestBody
            @Valid
                    BonusForm bonusForm,

            HttpServletResponse response
    ) {
        try {
            //- Set HTTP status -//
            response.setStatus(HttpStatus.CREATED.value());

            //- Get bonus type -/
            BonusType bonusType = this.bonusTypeService.findById(bonusForm.getBonusTypeId());
            //Create new Bonus
            Bonus bonus = new Bonus();

            //Set Data
            bonus.setBonusType(bonusType);
            bonus.setTitle(bonusForm.getTitle());
            bonus.setPrice(bonusForm.getPrice());
            bonus.setDescription(bonusForm.getDescription());

            //- Success. Return created bonus -//
            return this.bonusService.create(bonus);
        } catch (DataIntegrityViolationException e) {
            //- Failure. Can not to create bonus -//
            response.setStatus(HttpStatus.CONFLICT.value());
        }

        return null;
    }

    /**
     * Update already exist bonus.
     *
     * @param id        ID of Bonus
     * @param bonusForm Updated data
     * @param response  Use for set HTTP status
     * @return Updated bonus.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Bonus updateAction(
            @PathVariable("id")
                    Long id,

            @RequestBody
            @Valid
                    BonusForm bonusForm,

            HttpServletResponse response
    ) {
        //- Search origin bonus -//
        Bonus bonusOrigin = this.bonusService.findById(id);

        if (bonusOrigin == null) {
            //- Failure. Bonus not found -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        //- Update bonus -//
        try {
            //- Set new data -//
            bonusOrigin.setBonusType(this.bonusTypeService.findById(bonusForm.getBonusTypeId()));
            bonusOrigin.setTitle(bonusForm.getTitle());
            bonusOrigin.setPrice(bonusForm.getPrice());
            bonusOrigin.setDescription(bonusForm.getDescription());

            //- Success. Return updated bonus -//
            return this.bonusService.update(bonusOrigin);
        } catch (DataIntegrityViolationException e) {
            //- Failure. Can not to create bonus -//
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }

        return null;
    }

    /**
     * Delete bonus.
     *
     * @param id       Id of Bonus.
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
            //- Try to delete bonus -//
            this.bonusService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            // Failure. Bonus doesn't exists
            //- Set HTTP status -//
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
