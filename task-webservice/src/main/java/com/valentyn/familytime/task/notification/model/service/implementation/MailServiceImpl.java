/**
 * ** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 * *
 *
 * @copyright 2016 (c), by Valentine
 * @author <a href="mailto:valentunnamisnuk@gmail.com">Valentyn Namisnyk</a>
 * @date 2016-09-05 10:46 :: 2016-09-05 10::
 * @address /Ukraine/Ivano-Frankivsk/Rozhniw
 * *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

package com.valentyn.familytime.task.notification.model.service.implementation;

import com.valentyn.familytime.task.notification.model.entity.Contact;
import com.valentyn.familytime.task.notification.model.entity.Message;
import com.valentyn.familytime.task.notification.model.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * E-mail implementation of notification service.
 * Send e-mails.
 *
 * @version 1.0
 *
 * @see NotificationService
 */
@Service
public class MailServiceImpl implements NotificationService {
    /// *** Properties  *** ///
    /**
     * Provider for sending e-mails.
     */
    @Autowired
    private MailSender sender;


    /// *** Methods     *** ///

    /**
     * Send message.
     *
     * @param from    Contact of person who writes message
     * @param to      Contact of destination.
     * @param message Message for sending.
     */
    @Override
    public void send(Contact from, Contact to, Message message) {

        //- Create e-mail -//
        SimpleMailMessage email = new SimpleMailMessage();

        //- Set e-mail content -//
        email.setFrom(from.getAddress());
        email.setTo(to.getAddress());
        email.setSubject(message.getSubject());
        email.setText(message.getText());
        //TODO: add additional params

        this.sender.send(email);
    }
}
