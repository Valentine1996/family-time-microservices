/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-08-06 12:11 :: 2016-08-06 12::28
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(
        name = "complexity",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "family_id",
                                "type"
                        }
                )
        })
public class Complexity implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column(name = "id")
    protected Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "family_id")
    protected Family family;

    @NotBlank
    @Column(name = "type")
    @Length(max = 16)
    protected String type;

    @NotBlank
    @Column(name = "description")
    protected String description;

    /**
     * Default constructor.
     */
    public Complexity() {
    }

    /**
     * Constructor.
     *
     * @param family User's family
     * @param type complexity's type
     */
    public Complexity(Family family, String type) {
        this.family = family;
        this.type = type;
    }

    /**
     * Constructor.
     *
     * @param family User's family
     * @param type complexity's type
     * @param description complexity's description
     */
    public Complexity(Family family, String type, String description) {
        this.family = family;
        this.type = type;
        this.description = description;
    }

    //- SECTION :: GET -//

    /**
     * Get ID of the complexity.
     * @return Long ID of the complexity
     */
    public Long getId() {
        return id;
    }

    /**
     * Set ID of the complexity.
     *
     * @param id ID of the complexity
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get family of the complexity.
     * @return Family Complexity's family
     */
    public Family getFamily() {
        return family;
    }

    /**
     * Set family of the complexity.
     *
     * @param family ID of the complexity
     */
    public void setFamily(Family family) {
        this.family = family;
    }

    //- SECTION :: SET -//

    /**
     * Get type of the complexity.
     * @return String Complexity's type
     */
    public String getType() {
        return type;
    }

    /**
     * Set type of the complexity.
     *
     * @param type of the complexity
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get description of the complexity.
     * @return String Complexity's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description of the complexity.family
     *
     * @param description of the complexity
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
