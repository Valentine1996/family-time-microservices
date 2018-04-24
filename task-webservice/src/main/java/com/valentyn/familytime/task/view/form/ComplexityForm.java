package com.valentyn.familytime.task.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Form for complexity.
 *
 * @version 1.0
 */
public class ComplexityForm {

    @NotBlank
    @Length(max = 16)
    protected String type;

    @NotBlank
    protected String description;

    /**
     * Default constructor.
     */
    public ComplexityForm() {
    }

    /**
     * Constructor.
     *
     * @param type        Complexity's short name
     * @param description Complexity's description
     */
    public ComplexityForm(String type, String description) {
        this.type = type;
        this.description = description;
    }

    //- SECTION :: GET -//

    /**
     * Get type of complexity.
     *
     * @return String Complexity's type.
     */
    public String getType() {
        return type;
    }

    /**
     * Set type of the complexity.
     *
     * @param type of the complexity.
     */
    public void setType(String type) {
        this.type = type;
    }

    //- SECTION :: SET -//

    /**
     * Get description of complexity.
     *
     * @return String Complexity's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description of the complexity.
     *
     * @param description of the complexity.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
