package com.valentyn.familytime.task.model.service;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import com.valentyn.familytime.task.notification.model.entity.Email;
import com.valentyn.familytime.task.notification.model.entity.EmailAddress;
import com.valentyn.familytime.task.notification.model.service.NotificationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.Message;
import javax.mail.MessagingException;

import static org.junit.Assert.assertEquals;


public class NotificationServiceTest extends AbstractServiceTest {
    //- Mock SMTP server -//
    private static final GreenMail smtpServer =
            new GreenMail( new ServerSetup( 3025, null, "smtp" ));

    @Autowired
    NotificationService notificationService;

    /// *** Methods     *** ///
    /**
     * Prepare environment for test notification.
     */
    @Before
    @Override
    public void tearUp() throws Exception {
        super.tearUp();

        //- Turn on SMTP Server -//
        smtpServer.start();
    }

    @Test
    public void testSendingRecoveryLink() throws MessagingException {


        notificationService.send(
                new EmailAddress("iffamilytime@gmail.com"),
                new EmailAddress("valentunnamisnuk@gmail.com"),
                new Email(
                        "Registration successfull",
                        "Welcome to our big family"
                )
        );

        Message[] messages = smtpServer.getReceivedMessages();
        assertEquals(1, messages.length);
        assertEquals("Registration successfull", messages[0].getSubject());
        String body = GreenMailUtil.getBody(messages[0]);
        assertEquals("Welcome to our big family", body);
    }


}
