package com.coursework.modforge.service;

import com.coursework.modforge.dto.UserCreationDto;
import com.coursework.modforge.dto.UserDto;
import com.coursework.modforge.entity.User;
import com.coursework.modforge.exception.UserNotFoundException;
import com.coursework.modforge.mapper.UserMapper;
import com.coursework.modforge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    public List<UserDto> getAll(){
        List<User> users = userRepository.findAll();
        return  users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional
    public UserDto create(UserCreationDto userCreationDto){
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userCreationDto)));
    }
}
