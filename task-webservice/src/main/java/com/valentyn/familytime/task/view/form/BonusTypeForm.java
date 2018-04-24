/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2017-01-20 23:14:28
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Form for bonus type.
 *
 * @version 1.0
 */
public class BonusTypeForm {
    /// *** Properties  *** ///

    @NotBlank
    @Length(max = 16)
    protected String shortName;

    @NotBlank
    protected String description;

    @NotBlank
    @Length(max = 16)
    protected String iconName;

    /**
     * Default constructor.
     */
    public BonusTypeForm() {
    }

    /**
     * Constructor.
     *
     * @param shortName Bonus's short name
     * @param iconName Bonus's icon name
     */
    public BonusTypeForm(String shortName, String iconName) {
        this.shortName = shortName;
        this.iconName = iconName;
    }

    /**
     * Constructor.
     *
     * @param shortName Bonus's short name
     * @param description Bonus's description
     * @param iconName Bonus's icon name
     */
    public BonusTypeForm(String shortName, String description, String iconName) {
        this.shortName = shortName;
        this.description = description;
        this.iconName = iconName;
    }

    //- SECTION :: GET -//

    /**
     * Get short name of the bonus type.
     * @return String Bonus's short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Set short name of the bonus type.
     *
     * @param shortName of the bonus type
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Get description of the bonus type.
     * @return String Bonus's description
     */
    public String getDescription() {
        return description;
    }

    //- SECTION :: SET -//

    /**
     * Set description of the bonus type.
     *
     * @param description of the bonus type
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get icon name of the bonus type.
     * @return String Bonus's icon name
     */
    public String getIconName() {
        return iconName;
    }

    /**
     * Set icon name of the bonus type.
     *
     * @param iconName of the bonus type
     */
    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
