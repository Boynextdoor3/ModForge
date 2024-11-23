package com.coursework.modforge.service;

import com.coursework.modforge.dto.UserDto;
import com.coursework.modforge.entity.Role;
import com.coursework.modforge.entity.User;
import com.coursework.modforge.exception.ModTypeNotFoundException;
import com.coursework.modforge.exception.UserNotFoundException;
import com.coursework.modforge.mapper.UserMapper;
import com.coursework.modforge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with this username already exist");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exist");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserNotFound"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public UserDto getById(Long id){
        User user = userRepository
                .findById(id).orElseThrow(() -> new UserNotFoundException("User wit id " + id + "not found!"));
        return userMapper.toDto(user);
    }

    @Transactional
    public void delete(Long id){
        if (!userRepository.existsById(id)) {
            throw new ModTypeNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void giveModer(Long id){
        User user = userRepository
                .findById(id).orElseThrow(() -> new UserNotFoundException("User wit id " + id + "not found!"));
        user.setRole(Role.ROLE_MODER);
        save(user);
    }

}
