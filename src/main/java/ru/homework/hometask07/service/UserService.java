package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.dao.UserRepository;
import ru.homework.hometask07.dao.entity.UserEntity;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByID(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity createUser(UserEntity body) {
        return userRepository.save(body);
    }

    public UserEntity updateUser(Integer id, UserEntity body) {
        body.setId(id);
        return userRepository.save(body);
    }

    public void deleteUserByID(Integer id) {
        userRepository.deleteById(id);
    }

    public UserEntity getUserByLogin(String login) {
        return userRepository.findUserEntityByLoginEquals(login).orElse(null);
    }
}
