package com.valentyn.familytime.task.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Form for parentTask type.
 *
 * @version 1.0
 */
public class TaskTypeForm {

    @NotBlank
    @Length(max = 16)
    protected String shortName;

    @NotBlank
    protected String description;

    /**
     * Default constructor.
     */
    public TaskTypeForm() {
    }

    /**
     * Constructor.
     *
     * @param shortName   Task's type short name
     * @param description Task's type description
     */
    public TaskTypeForm(String shortName, String description) {
        this.shortName = shortName;
        this.description = description;
    }

    //- SECTION :: GET -//

    /**
     * Get short name of parentTask type.
     *
     * @return String Task type.
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Set short name of the parentTask type.
     *
     * @param shortName of the parentTask type.
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    //- SECTION :: SET -//

    /**
     * Get description of parentTask type.
     *
     * @return String Task's type description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description of the parentTask type.
     *
     * @param description of the parentTask type.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
