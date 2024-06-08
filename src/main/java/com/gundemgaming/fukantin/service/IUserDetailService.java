package com.gundemgaming.fukantin.service;

import com.gundemgaming.fukantin.dto.UserDetailDto;

public interface IUserDetailService {
    UserDetailDto getUserDetail(Long userId);
    UserDetailDto updateUserDetail(UserDetailDto userDetailDto);
}
