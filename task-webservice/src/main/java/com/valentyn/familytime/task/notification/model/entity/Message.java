/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-09-05 10:18 :: 2016-09-05 10::21
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.notification.model.entity;

/**

 /**
 * Message for sending.
 *
 * @version 1.0
 */
public interface Message {
    /**
     * Get subject.
     *
     * @return String
     */
    String getSubject();

    /**
     * Get content.
     *
     * @return String
     */
    String getText();
}
