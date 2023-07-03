package ru.homework.hometask07.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.controller.dto.UserDto;
import ru.homework.hometask07.dao.GenericRepository;
import ru.homework.hometask07.dao.UserRepository;
import ru.homework.hometask07.dao.entity.UserEntity;
import ru.homework.hometask07.mapper.GenericMapper;

import java.time.LocalDateTime;

@Service
public class UserService extends GenericService<UserEntity, UserDto> {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(GenericRepository<UserEntity> repository,
                       GenericMapper<UserEntity, UserDto> mapper,
                       UserRepository userRepository,
                       RoleService roleService,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(repository, mapper);
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserEntity create(UserEntity body) {
        body.setRole(roleService.getRoleByID(1L));
        body.setPassword(bCryptPasswordEncoder.encode(body.getPassword()));
        body.setCreatedWhen(LocalDateTime.now());
        return userRepository.save(body);
    }

//    public UserEntity update(Long id, UserEntity body) {
//        body.setId(id);
//        return userRepository.save(body);
//    }

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
