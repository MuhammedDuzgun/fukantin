package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.UserDetailDto;
import com.gundemgaming.fukantin.exception.ResourceNotFoundException;
import com.gundemgaming.fukantin.model.User;
import com.gundemgaming.fukantin.model.UserDetail;
import com.gundemgaming.fukantin.repository.IUserDetailRepository;
import com.gundemgaming.fukantin.repository.IUserRepository;
import com.gundemgaming.fukantin.service.IUserDetailService;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements IUserDetailService {

    private IUserDetailRepository userDetailRepository;
    private IUserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserDetailService(IUserDetailRepository userDetailRepository, IUserRepository userRepository, ModelMapper modelMapper) {
        this.userDetailRepository = userDetailRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetailDto getUserDetail(Long userId) {
        //check is user exists
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));

        UserDetail userDetail = user.getUserDetail();

        return modelMapper.map(userDetail, UserDetailDto.class);
    }

    @Override
    public UserDetailDto updateUserDetail(UserDetailDto userDetailDto, Long userId) {
        //check is user exists
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));

        UserDetail userDetail = user.getUserDetail();

        userDetail.setDepartment(userDetailDto.getDepartment());
        userDetail.setInstagram(userDetailDto.getInstagram());
        userDetail.setBiography(userDetailDto.getBiography());

        userDetailRepository.save(userDetail);

        return modelMapper.map(userDetail, UserDetailDto.class);
    }
}
