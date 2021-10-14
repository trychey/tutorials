package com.baeldung.hexagonal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class HttpApi {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{username}")
    public User find(@PathVariable String username) {
        return userService.find(username);
    }
}
