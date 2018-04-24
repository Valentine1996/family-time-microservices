/**
 * Created by Andrii Gaidychuk on 25.07.2016.
 */

package com.valentyn.familytime.task.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * Class for reflect table bonus_type from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(
        name = "bonus_type",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "family_id",
                                "short_name"
                        }
                )
        })
public class BonusType implements Serializable {

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
    @Column(name = "short_name")
    @Length(max = 16)
    protected String shortName;

    @NotBlank
    @Column(name = "description")
    protected String description;

    @NotBlank
    @Column(name = "icon_name")
    @Length(max = 16)
    protected String iconName;

    /**
     * Default constructor.
     */
    public BonusType() {
    }

    /**
     * Constructor.
     *
     * @param family    Bonus's family
     * @param shortName Bonus's short name
     * @param iconName  Bonus's icon name
     */
    public BonusType(Family family, String shortName, String iconName) {
        this.family = family;
        this.shortName = shortName;
        this.iconName = iconName;
    }

    /**
     * Constructor.
     *
     * @param family      Bonus's family
     * @param shortName   Bonus's short name
     * @param description Bonus's description
     * @param iconName    Bonus's icon name
     */
    public BonusType(Family family, String shortName, String description, String iconName) {
        this.family = family;
        this.shortName = shortName;
        this.description = description;
        this.iconName = iconName;
    }

    //- SECTION :: GET -//

    /**
     * Get ID of the bonus_type.
     *
     * @return Long ID of the Bonuses
     */
    public Long getId() {
        return id;
    }

    /**
     * Set ID of the bonus type.
     *
     * @param id ID of the bonus type
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get family of the bonus type.
     *
     * @return Family Bonus's family
     */
    public Family getFamily() {
        return family;
    }

    /**
     * Set family of the bonus type.
     *
     * @param family ID of the bonus type
     */
    public void setFamily(Family family) {
        this.family = family;
    }

    /**
     * Get short name of the bonus type.
     *
     * @return String Bonus's short name
     */
    public String getShortName() {
        return shortName;
    }

    //- SECTION :: SET -//

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
     *
     * @return String Bonus's description
     */
    public String getDescription() {
        return description;
    }

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
     *
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
