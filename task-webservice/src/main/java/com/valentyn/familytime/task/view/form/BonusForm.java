/**
 * Created by Andrii Gaidychuk on 15.09.2016.
 */

package com.valentyn.familytime.task.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Form for bonus type.
 *
 * @version 1.0
 */
public class BonusForm {
    /// *** Properties  *** ///

    @NotNull
    protected Long bonusTypeId;

    @NotBlank
    @Length(max = 64)
    protected String title;

    @NotNull
    protected String description;

    @NotNull
    protected Integer price;

    /**
     * Default constructor.
     */
    public BonusForm() {
    }

    /**
     * Constructor.
     *
     * @param bonusTypeId BonusType's Id
     * @param price       Bonus's price
     */
    public BonusForm(Long bonusTypeId, Integer price) {
        this.bonusTypeId = bonusTypeId;
        this.price = price;
    }

    /**
     * Constructor.
     *
     * @param bonusTypeId BonusType's Id
     * @param title       Bonu's title
     * @param price       Bonus's price
     * @param description Bonus's description
     */
    public BonusForm(Long bonusTypeId, String title, Integer price, String description) {
        this.bonusTypeId = bonusTypeId;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    //- SECTION :: GET -//

    /**
     * Get BonusType's Id.
     *
     * @return bonusType Type of the bonus
     */
    public Long getBonusTypeId() {
        return bonusTypeId;
    }

    /**
     * Get Title of the bonus.
     *
     * @return String Title of the bonus
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set Title of the bonus.
     *
     * @param title Title of the bonus
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get Description of the bonus.
     *
     * @return String Description of the Bonus
     */
    public String getDescription() {
        return description;
    }

    //- SECTION :: SET -//

    /**
     * Set Description of the bonus.
     *
     * @param description of the bonus
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get price of the bonus.
     *
     * @return Integer Price of the Bonus
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Set Price of the bonus.
     *
     * @param price Price of the bonus
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Set BonusType's Id.
     *
     * @param bonusTypeId BonusType's Id
     */
    public void setBonusType(Long bonusTypeId) {
        this.bonusTypeId = bonusTypeId;
    }
}
