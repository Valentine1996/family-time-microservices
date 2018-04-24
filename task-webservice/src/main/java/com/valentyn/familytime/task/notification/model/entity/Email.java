/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-09-05 10:22 :: 2016-09-05 10::28
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.notification.model.entity;

/**
 * E-mail message.
 *
 * @version 1.0
 */
public class Email implements Message {
    /**
     * Email's subject.
     */
    private String subject;

    /**
     * Content.
     */
    private String text;


    /**
     * Create a new email.
     *
     * @param subject    Subject of message.
     * @param text       Body of message.
     */
    public Email(String subject, String text) {
        //- Initialization -//
        this.subject = subject;
        this.text = text;
    }

    /**
     * Get subject of message.
     *
     * @return String
     */
    @Override
    public String getSubject() {
        return this.subject;
    }

    /**
     * Get content of message(body).
     *
     * @return String
     */
    @Override
    public String getText() {
        return this.text;
    }
}
