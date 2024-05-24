package com.gundemgaming.fukantin.controller;

import com.gundemgaming.fukantin.dto.UserAuthDto;
import com.gundemgaming.fukantin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserAuthDto> createUser(@RequestBody UserAuthDto userAuthDto) {
        UserAuthDto createdUser = userService.createUser(userAuthDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}