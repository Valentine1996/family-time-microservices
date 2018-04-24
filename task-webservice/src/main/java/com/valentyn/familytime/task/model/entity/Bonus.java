/**
 * Created by Andrii Gaidychuk on 15.09.2016.
 */

package com.valentyn.familytime.task.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Class for reflect table bonus from persistence layout
 *
 * @version 1.0
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "bonus")
public class Bonus implements Serializable {

    /// *** Properties  *** ///
    @Id
    @GeneratedValue
    @Column(name = "id")
    protected Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "bonus_type_id")
    protected BonusType bonusType;

    @NotBlank
    @Column(name = "title")
    @Length(max = 64)
    protected String title;

    @Column(name = "description")
    protected String description;

    @NotNull
    @Column(name = "price")
    protected Integer price;

    /**
     * Default constructor.
     */
    public Bonus() {
    }

    /**
     * Constructor.
     *
     * @param bonusType Bonus's type
     * @param title     Bonus's title
     * @param price     Bonus's price
     */
    public Bonus(BonusType bonusType, String title, Integer price) {
        this.bonusType = bonusType;
        this.title = title;
        this.price = price;
    }

    /**
     * Constructor.
     *
     * @param bonusType   Bonus's type
     * @param title       Bonus's title
     * @param price       Bonus's price
     * @param description Bonus's description
     */
    public Bonus(BonusType bonusType, String title, Integer price, String description) {
        this.bonusType = bonusType;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    //- SECTION :: GET -//

    /**
     * Get ID of the bonus.
     *
     * @return Long ID of the Bonus
     */
    public Long getId() {
        return id;
    }

    /**
     * Set ID of the bonus.
     *
     * @param id ID of the bonus
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get Type of the bonus.
     *
     * @return BonusType Type of the Bonus
     */
    public BonusType getBonusType() {
        return bonusType;
    }

    /**
     * Set Type of the bonus.
     *
     * @param bonusType Type of the bonus
     */
    public void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
    }

    /**
     * Get Title of the bonus.
     *
     * @return String Title of the Bonus
     */
    public String getTitle() {
        return title;
    }

    //- SECTION :: SET -//

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
}

