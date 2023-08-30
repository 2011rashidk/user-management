package com.happiest.minds.usermanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    public String test() {
        log.info("User Management Application");
        return "User Management Application";
    }

}
