package com.gundemgaming.fukantin.controller;

import com.gundemgaming.fukantin.dto.UserAuthDto;
import com.gundemgaming.fukantin.dto.UserDto;
import com.gundemgaming.fukantin.service.IUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserAuthDto userAuthDto) {
        UserAuthDto createdUser = userService.createUser(userAuthDto);
        return new ResponseEntity<>("User Created Successfuly", HttpStatus.CREATED);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserAuthDto userAuthDto,
                                             @PathVariable(name = "id")  Long userId) {
        userService.updateUser(userAuthDto, userId);
        return new ResponseEntity<>("User Updated Successfuly", HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Deleted Successfuly", HttpStatus.OK);
    }

}