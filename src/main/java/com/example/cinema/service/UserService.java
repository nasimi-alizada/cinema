package com.example.cinema.service;

import com.example.cinema.dao.entity.UserEntity;
import com.example.cinema.dao.repository.UserRepository;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.mapper.UserMapper;
import com.example.cinema.model.request.UserRequest;
import com.example.cinema.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cinema.mapper.UserMapper.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::buildUserResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        var userEntity = fetchUserIfExist(id);
        return buildUserResponse(userEntity);
    }

    public void saveUser(UserRequest userRequest) {

        userRepository.save(buildUserEntity(userRequest));
    }

    public void updateUser(Long id, UserRequest userRequest) {
        var userEntity = fetchUserIfExist(id);
        buildUpdateUserEntity(userRequest, userEntity);
        userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        fetchUserIfExist(id);
        userRepository.deleteById(id);

    }


    private UserEntity fetchUserIfExist(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("USER_NOT_FOUND")
        );

    }
}
