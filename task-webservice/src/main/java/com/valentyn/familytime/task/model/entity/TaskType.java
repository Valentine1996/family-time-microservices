/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-08-06 11:33 :: 2016-08-06 11::37
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Class for reflect table user from persistence layout
 *
 * @version 1.0
 */
@Entity
@Table(
        name = "task_type",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "family_id",
                                "shortName"
                        }
                )
        })
public class TaskType implements Serializable {
    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column(name = "id")
    protected Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "family_id")
    protected Family family;

    @NotNull
    @Column(name = "shortName")
    @Length(max = 16)
    protected String shortName;

    @Column(name = "description")
    protected String description;

    /**
     * Default constructor.
     */
    public TaskType() {
    }

    /**
     * Constructor.
     *
     * @param family User's family
     * @param shortName Task's short name
     */
    public TaskType(Family family, String shortName) {
        this.family = family;
        this.shortName = shortName;
    }

    /**
     * Constructor.
     *
     * @param family User's family
     * @param shortName Task's short name
     * @param description Task's description
     */
    public TaskType(Family family, String shortName, String description) {
        this.family = family;
        this.shortName = shortName;
        this.description = description;
    }

    //- SECTION :: GET -//

    /**
     * Get ID of the task_type.
     * @return Long ID of the parentTask
     */
    public Long getId() {
        return id;
    }

    /**
     * Set ID of the parentTask type.
     *
     * @param id ID of the parentTask type
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get family of the parentTask type.
     * @return Family Task's family
     */
    public Family getFamily() {
        return family;
    }

    /**
     * Set family of the parentTask type.
     *
     * @param family ID of the parentTask type
     */
    public void setFamily(Family family) {
        this.family = family;
    }

    //- SECTION :: SET -//

    /**
     * Get short name of the parentTask type.
     * @return String Task's short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Set short name of the parentTask type.
     *
     * @param shortName of the parentTask type
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Get description of the parentTask type.
     * @return String Tasks's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description of the parentTask type.
     *
     * @param description of the parentTask type
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
