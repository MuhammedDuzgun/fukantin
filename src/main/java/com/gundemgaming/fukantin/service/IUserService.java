package com.gundemgaming.fukantin.service;

import com.gundemgaming.fukantin.dto.UserDto;
import com.gundemgaming.fukantin.dto.UserAuthDto;

import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();
    UserDto getUser(Long userId);
    UserAuthDto createUser(UserAuthDto userAuthDto);
    UserAuthDto updateUser(UserAuthDto userAuthDto, Long userId);
    void deleteUser(Long userId);
}
