/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-09-22 23:24:28 :: 2016-09-22 23:36:28
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.security.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Form for access recovery.
 *
 * @version 1.0
 */
public class PasswordRecoveryForm {

    @NotBlank
    protected String hash;

    @Length(min = 8, max = 80)
    @NotBlank
    protected String password;

    /**
     * Default constructor.
     */
    public PasswordRecoveryForm() {
    }

    /**
     * Constructor.
     *
     * @param hash One-time hash.
     * @param password New password.
     */
    public PasswordRecoveryForm(String hash, String password) {
        this.hash = hash;
        this.password = password;
    }

    //- SECTION :: GET -//

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    //- SECTION :: SET -//

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
