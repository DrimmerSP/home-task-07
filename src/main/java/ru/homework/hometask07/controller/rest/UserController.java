package ru.homework.hometask07.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.controller.dto.FilmDto;
import ru.homework.hometask07.controller.dto.UserDto;
import ru.homework.hometask07.mapper.FilmMapper;
import ru.homework.hometask07.mapper.UserMapper;
import ru.homework.hometask07.service.OrderService;
import ru.homework.hometask07.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Пользователи системы: ")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderService orderService;
    private final FilmMapper filmMapper;

    @Operation(description = "Получить список всех пользователей.")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::entityToDto)
                .toList();
    }

    @Operation(description = "Полузить имя пользователя по ID.")
    @GetMapping("/{id}")
    public UserDto getUserByID(@PathVariable Integer id) {
        return userMapper.entityToDto(userService.getUserByID(id));
    }

    @Operation(description = "Добавить пользователя.")
    @PostMapping
    public UserDto createUser(@RequestBody UserDto body) {
        return userMapper.entityToDto(userService.createUser(userMapper.dtoToEntity(body)));
    }

    @Operation(description = "Обновить информацию о пользователе.")
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Integer id, @RequestBody UserDto body) {
        return userMapper.entityToDto(userService.updateUser(id, userMapper.dtoToEntity(body)));
    }

    @Operation(description = "Удалить запись о пользователе.")
    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable Integer id) {
        userService.deleteUserByID(id);
    }

    @GetMapping("/{id}/inuse")
    public List<FilmDto> getFilmsInUse(@PathVariable(value = "id") Integer userId) {
        return orderService.getFilmsInUse(userId).stream()
                .map(filmMapper::entityToDto)
                .toList();
    }
}
