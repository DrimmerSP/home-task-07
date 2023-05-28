package ru.homework.hometask07.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.dao.UserRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.UserEntity;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Пользователи системы: ")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @Operation(description = "Получить список всех пользователей.")
    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    @Operation(description = "Полузить имя пользователя по ID.")
    @GetMapping("/{id}")
    public UserEntity getUserByID(@PathVariable Integer id){
        return userRepository.findById(id).orElse(null);
    }

    @Operation(description = "Добавить пользователя.")
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity body){
        return userRepository.save(body);
    }

    @Operation(description = "Обновить информацию о пользователе.")
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Integer id, @RequestBody UserEntity body){
        body.setId(id);
        return userRepository.save(body);
    }

    @Operation(description = "Удалить запись о пользователе.")
    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable Integer id){
        userRepository.deleteById(id);
    }
}
