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

import java.util.*;
import java.util.stream.Collectors;

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
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).get();
        return modelMapper.map(user, UserDto.class);
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
        //check is user exists
        if(!userRepository.existsById(userId)) {
            //boyle bir kullanıcı yok hata mesajı fırlat
        }

        User userToUpdate = userRepository.findById(userId).get();

        userToUpdate.setUsername(userAuthDto.getUsername());
        userToUpdate.setPassword(userAuthDto.getPassword());

        userRepository.save(userToUpdate);

        return modelMapper.map(userToUpdate, UserAuthDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Boyle bir Kullanıcı Yok"));

        userRepository.delete(userToDelete);
    }
}
