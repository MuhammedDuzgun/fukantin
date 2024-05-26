package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.UserDetailDto;
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
    public UserDetailDto getUserDetail(Long userId, Long userDetailId) {
        //check is user exists
        if(!userRepository.existsById(userId)) {
            //boyle bir kullanici yok
        }

        //check is userDetailId belongs to user
        User user = userRepository.findById(userId).get();
        if(user.getUserDetail().getId() != userDetailId) {
            //userDetail user'a ait degil
        }

        UserDetail userDetail = userDetailRepository.findById(userDetailId)
                .orElseThrow(() -> new IllegalArgumentException("Boyle bir userDetail yok"));

        return modelMapper.map(userDetail, UserDetailDto.class);
    }

    @Override
    public UserDetailDto updateUserDetail(UserDetailDto userDetailDto, Long userId, Long userDetailId) {
        //check is user exists
        if(!userRepository.existsById(userId)) {
            //boyle bir kullanici yok
        }

        //check is userDetailId belongs to user
        User user = userRepository.findById(userId).get();
        if(user.getUserDetail().getId() != userDetailId) {
            //userDetail user'a ait degil
        }

        UserDetail userDetail = userDetailRepository.findById(userDetailId)
                .orElseThrow(() -> new IllegalArgumentException("Boyle bir userDetail yok"));

        userDetail.setDepartment(userDetailDto.getDepartment());
        userDetail.setInstagram(userDetailDto.getInstagram());
        userDetail.setBiography(userDetailDto.getBiography());

        userDetailRepository.save(userDetail);

        return modelMapper.map(userDetail, UserDetailDto.class);
    }
}
