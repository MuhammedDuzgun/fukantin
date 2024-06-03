package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.UserAuthDto;
import com.gundemgaming.fukantin.exception.FuKantinAPIException;
import com.gundemgaming.fukantin.model.Role;
import com.gundemgaming.fukantin.model.User;
import com.gundemgaming.fukantin.repository.IRoleRepository;
import com.gundemgaming.fukantin.repository.IUserRepository;
import com.gundemgaming.fukantin.security.JwtTokenProvider;
import com.gundemgaming.fukantin.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService implements IAuthService {

    private AuthenticationManager authenticationManager;
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthService(AuthenticationManager authenticationManager,
                       IUserRepository userRepository,
                       IRoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(UserAuthDto userAuthDto) {

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(userAuthDto.getUsername(), userAuthDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(UserAuthDto userAuthDto) {
        //check is username exists
        if(userRepository.existsByUsername(userAuthDto.getUsername())) {
            throw new FuKantinAPIException("Username with : " + userAuthDto.getUsername() + " is already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(userAuthDto.getUsername());
        user.setPassword(passwordEncoder.encode(userAuthDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRole("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfuly";
    }

}
