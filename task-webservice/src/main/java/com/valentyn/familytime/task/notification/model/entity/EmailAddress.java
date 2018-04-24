/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-09-05 10:29 :: 2016-09-05 10::28
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.notification.model.entity;

/**
 * E-mail address.
 *
 * @version 1.0
 */
public class EmailAddress implements Contact {
    /// *** Properties  *** ///
    /**
     * Valid e-mail address.
     */
    private String address;


    /// *** Methods     *** ///

    /**
     * Default.
     */
    public EmailAddress() {
    }

    /**
     * Create a new E-mail contact.
     *
     * @param address    E-mail address
     */
    public EmailAddress(String address) {
        this.address = address;
    }

    /**
     * Get address of contact.
     *
     * @return String
     */
    @Override
    public String getAddress() {
        return this.address;
    }


    /**
     * Set address of contact.
     *
     * @return String
     */
    public void setAddress(String address) {

        this.address = address;
    }


}
