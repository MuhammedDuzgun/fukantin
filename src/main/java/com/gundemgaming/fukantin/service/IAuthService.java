package com.gundemgaming.fukantin.service;

import com.gundemgaming.fukantin.dto.UserAuthDto;

public interface IAuthService {
    String login(UserAuthDto userAuthDto);
    String register(UserAuthDto userAuthDto);
}
