package com.gundemgaming.fukantin.service;

import com.gundemgaming.fukantin.dto.UserDetailDto;

public interface IUserDetailService {
    UserDetailDto getUserDetail(Long userId, Long userDetailId);
    UserDetailDto updateUserDetail(UserDetailDto userDetailDto, Long userId, Long userDetailId);
}
