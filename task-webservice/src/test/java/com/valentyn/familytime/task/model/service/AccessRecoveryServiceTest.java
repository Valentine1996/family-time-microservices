package com.valentyn.familytime.task.model.service;

import com.valentyn.familytime.task.model.entity.User;
import com.valentyn.familytime.task.notification.model.entity.EmailAddress;
import com.valentyn.familytime.task.security.model.entity.RecoveryAccess;
import com.valentyn.familytime.task.security.model.service.AccessRecoveryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


/**
 * Testing email sending in prod. Do not modify.
 */
@Ignore
public class AccessRecoveryServiceTest extends AbstractServiceTest {
    /// *** Services     *** ///
    @Autowired
    AccessRecoveryService accessRecoveryService;

    @Autowired
    UserService userService;

    /**
     * Encoder for create hash of password.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Prepare environment for test access recovery.
     */
    @Before
    @Override
    public void tearUp() throws Exception {
        super.tearUp();
    }

    @Ignore
    @Test
    public void checkPasswordRecoveryMailMessage() throws IOException {
        accessRecoveryService.lostAccess(new EmailAddress("valentunnamisnuk@gmail.com"));
        assertEquals(1,1);
    }

    /**
     * Test getting valid access token.
     */
    @Test
    public void positiveTestCheckingToken() {
        //- Generate one-time hash -//
        String hashForPositiveTest = this.passwordEncoder.encode(
                UUID.randomUUID().toString()
        );

        //Data for positive test
        //Get first user
        User user =  userService.findByUsername("valentunnamisnuk@gmail.com");

        //Save access token
        accessRecoveryService.create(new RecoveryAccess(user,hashForPositiveTest,
                                                        OffsetDateTime.now().plusMinutes(15)));

        //Check valid recovery access
        RecoveryAccess recoveryAccess = accessRecoveryService.getValidAccess(hashForPositiveTest);

        Assert.assertNotNull(recoveryAccess);
    }

    /**
     * Test saving invalid recovery access.
     */
    @Test(expected = org.springframework.transaction.TransactionSystemException.class)
    public void negativeTestCheckingToken() {
        //- Generate one-time hash -//
        String hashForNegativeTest = this.passwordEncoder.encode(
                UUID.randomUUID().toString()
        );

        //Data for positive test
        //Get first user
        User user =  userService.findByUsername("valentunnamisnuk@gmail.com");

        //Save access token
        accessRecoveryService.create(new RecoveryAccess(user,hashForNegativeTest,
                                                        OffsetDateTime.now().minusMinutes(15)));
    }
}
