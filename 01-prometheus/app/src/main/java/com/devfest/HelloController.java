package com.devfest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
class HelloController {

    @GetMapping("/metrics")
    String sayHello(@RequestParam(defaultValue = "World") String name) {
        return "Hello: " + name + " " + LocalDateTime.now();
    }
}
