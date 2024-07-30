package com.example.usersync.controller;

import com.example.usersync.User;
import com.example.usersync.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api(value = "User Magagement System", description = "Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create a new user", response = User.class)
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createdUser(user);
    }
}
