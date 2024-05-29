package com.gundemgaming.fukantin.controller;

import com.gundemgaming.fukantin.dto.UserDetailDto;
import com.gundemgaming.fukantin.service.IUserDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserDetailController {

    private IUserDetailService userDetailService;

    @Autowired
    public UserDetailController(IUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/users/{userId}/userdetails")
    public ResponseEntity<UserDetailDto> getUserDetail(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(userDetailService.getUserDetail(userId));
    }

    @PutMapping("/users/{userId}/userdetails")
    public ResponseEntity<UserDetailDto> updateUserDetail(@Valid @RequestBody UserDetailDto userDetailDto,
                                                          @PathVariable(name = "userId") Long userId) {

        return ResponseEntity.ok(userDetailService.updateUserDetail(userDetailDto, userId));
    }

}
