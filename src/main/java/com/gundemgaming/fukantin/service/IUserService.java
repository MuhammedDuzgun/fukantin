package com.gundemgaming.fukantin.service;

import com.gundemgaming.fukantin.dto.UserDto;
import com.gundemgaming.fukantin.dto.UserRegisterDto;

import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();
    UserDto getUser(Long userId);
    UserRegisterDto createUser(UserRegisterDto userRegisterDto);
    UserRegisterDto updateUser(UserRegisterDto userRegisterDto, Long userId);
    void deleteUser(Long userId);
}
