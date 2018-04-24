package com.valentyn.familytime.task.controller;

import com.valentyn.familytime.task.model.repository.RoleRepository;
import com.valentyn.familytime.task.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valentyn on 7/5/16.
 */
@RestController
public class HelloController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
