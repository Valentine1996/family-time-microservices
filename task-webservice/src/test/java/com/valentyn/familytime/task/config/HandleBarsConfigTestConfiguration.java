package com.valentyn.familytime.task.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;

@Profile("development")
@TestConfiguration
public class HandleBarsConfigTestConfiguration extends HandleBarsConfig {

}
