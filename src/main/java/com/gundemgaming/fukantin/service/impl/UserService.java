package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.UserAuthDto;
import com.gundemgaming.fukantin.dto.UserDto;
import com.gundemgaming.fukantin.model.Role;
import com.gundemgaming.fukantin.model.User;
import com.gundemgaming.fukantin.repository.IRoleRepository;
import com.gundemgaming.fukantin.repository.IUserRepository;
import com.gundemgaming.fukantin.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(IUserRepository userRepository, IRoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDto getUser(Long userId) {
        return null;
    }

    @Override
    public UserAuthDto createUser(UserAuthDto userAuthDto) {
        //check is username exists
        if(userRepository.existsByUsername(userAuthDto.getUsername())) {
            //gerekli islemler
        }

        User user = new User();
        user.setUsername(userAuthDto.getUsername());
        user.setPassword(userAuthDto.getPassword());

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRole("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
        return modelMapper.map(user, UserAuthDto.class);
    }

    @Override
    public UserAuthDto updateUser(UserAuthDto userAuthDto, Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
