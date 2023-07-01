package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.dao.UserRepository;
import ru.homework.hometask07.dao.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByID(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity createUser(UserEntity body) {
        body.setRole(roleService.getRoleByID(1));
        body.setPassword(bCryptPasswordEncoder.encode(body.getPassword()));
        body.setCreatedWhen(LocalDateTime.now());
        return userRepository.save(body);
    }

    public UserEntity updateUser(Integer id, UserEntity body) {
        body.setId(id);
        return userRepository.save(body);
    }

    public void deleteUserByID(Integer id) {
        userRepository.deleteById(id);
    }

    public UserEntity getUserByLogin(final String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    public UserEntity getUserByEmail(final String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean checkPassword(String password, UserDetails foundUser) {
        return bCryptPasswordEncoder.matches(password, foundUser.getPassword());
    }
}
