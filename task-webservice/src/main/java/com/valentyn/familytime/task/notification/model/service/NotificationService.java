/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-09-05 10:43 :: 2016-09-05 10:46
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.notification.model.service;

import com.valentyn.familytime.task.notification.model.entity.Contact;
import com.valentyn.familytime.task.notification.model.entity.Message;

public interface NotificationService {

    /**
     * Send message.
     *
     * @param from       Contact of sender
     * @param to         Contact of recipient.
     * @param message    Message for sending.
     */
    void send(Contact from, Contact to, Message message);
}
