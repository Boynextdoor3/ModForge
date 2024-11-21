package com.coursework.modforge.service;

import com.coursework.modforge.entity.Role;
import com.coursework.modforge.entity.User;
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
    private final UserMapper userMapper;


//    public UserDto getById(Long id){
//        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
//        return userMapper.toDto(user);
//    }

//    public List<UserDto> getAll(){
//        List<User> users = userRepository.findAll();
//        return  users.stream()
//                .map(userMapper::toDto)
//                .toList();
//    }

//    @Transactional
//    public UserDto create(UserCreationDto userCreationDto){
//        return userMapper.toDto(userRepository.save(userMapper.toEntity(userCreationDto)));
//    }

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

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ADMIN);
        save(user);
    }

}
