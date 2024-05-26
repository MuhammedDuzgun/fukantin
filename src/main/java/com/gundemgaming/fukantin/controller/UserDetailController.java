package com.gundemgaming.fukantin.controller;

import com.gundemgaming.fukantin.dto.UserDetailDto;
import com.gundemgaming.fukantin.service.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/userdetails/{userDetailId}")
public class UserDetailController {

    private IUserDetailService userDetailService;

    @Autowired
    public UserDetailController(IUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping
    public ResponseEntity<UserDetailDto> getUserDetail(@PathVariable(name = "userId") Long userId,
                                                       @PathVariable(name = "userDetailId") Long userDetailId) {
        return ResponseEntity.ok(userDetailService.getUserDetail(userId, userDetailId));
    }

    @PutMapping
    public ResponseEntity<UserDetailDto> updateUserDetail(@RequestBody UserDetailDto userDetailDto,
                                                          @PathVariable(name = "userId") Long userId,
                                                          @PathVariable(name = "userDetailId") Long userDetailId) {
        return ResponseEntity.ok(userDetailService.updateUserDetail(userDetailDto, userId, userDetailId));
    }

}
