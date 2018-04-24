package com.valentyn.familytime.task.config.security;

import com.valentyn.familytime.task.config.properties.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
@PropertySource("classpath:config/mail/config.properties")
public class MailConfig {

    @Autowired
    Environment env;

    @Autowired
    MailProperties mailProperties;
    /**
     * Mail Sender .
     *
     * @return MailSender
     */
    @Bean
    public MailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(mailProperties.getHost());
        javaMailSender.setPort(mailProperties.getPort());
        javaMailSender.setUsername(mailProperties.getUsername());
        javaMailSender.setPassword(mailProperties.getPassword());

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    /**
     * Get mail properties.
     *
     * @return Properties
     */
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol",
                env.getProperty("mail.transport.protocol"));
        properties.setProperty("mail.smtp.auth",
                env.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable",
                env.getProperty("mail.smtp.starttls.enable"));
        properties.setProperty("mail.debug", env.getProperty("mail.debug"));
        return properties;
    }
}
