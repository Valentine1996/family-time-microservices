/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-07-10 12:00:59 :: 2014-07-08 12:24:37
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.controller;

import com.valentyn.familytime.task.model.entity.Family;
import com.valentyn.familytime.task.model.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for work with families.
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/family/")
public class FamilyController {

    @Autowired
    protected FamilyService familyService;

    /// *** Methods     *** ///

    /**
     * Get list of families.
     *
     * @return List of families.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Family> findAll() {
        return this.familyService.findAll();
    }
}
