package ru.homework.hometask07.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.config.jwt.JwtTokenUtil;
import ru.homework.hometask07.controller.dto.FilmDto;
import ru.homework.hometask07.controller.dto.LoginDto;
import ru.homework.hometask07.controller.dto.UserDto;
import ru.homework.hometask07.mapper.FilmMapper;
import ru.homework.hometask07.mapper.UserMapper;
import ru.homework.hometask07.service.OrderService;
import ru.homework.hometask07.service.UserService;
import ru.homework.hometask07.service.userdetails.CustomUserDetailsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Пользователи системы: ")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderService orderService;
    private final FilmMapper filmMapper;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Operation(description = "Получить список всех пользователей.")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return  userMapper.toDTOs(userService.listAll());
    }

    @Operation(description = "Полузить имя пользователя по ID.")
    @GetMapping("/{id}")
    public UserDto getUserByID(@PathVariable Long id) {
        return userMapper.toDTO(userService.getOne(id));
    }

    @Operation(description = "Добавить пользователя.")
    @PostMapping
    public UserDto createUser(@RequestBody UserDto body) {
        return userMapper.toDTO(userService.create(userMapper.toEntity(body)));
    }

    @Operation(description = "Обновить информацию о пользователе.")
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto body) {
        return userMapper.toDTO(userService.update(id, userMapper.toEntity(body)));
    }

    @Operation(description = "Удалить запись о пользователе.")
    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/inuse")
    public List<FilmDto> getFilmsInUse(@PathVariable(value = "id") Integer userId) {
        return filmMapper.toDTOs(orderService.getFilmsInUse(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody LoginDto loginDTO) {
        Map<String, Object> response = new HashMap<>();
        UserDetails foundUser = customUserDetailsService.loadUserByUsername(loginDTO.getLogin());
        log.info("foundUser: {}", foundUser);
        if (!userService.checkPassword(loginDTO.getPassword(), foundUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ошибка авторизации! \n Неверный пароль...");
        }
        String token = jwtTokenUtil.generateToken(foundUser);
        response.put("token", token);
        response.put("username", foundUser.getUsername());
        response.put("role", foundUser.getAuthorities());
        return ResponseEntity.ok().body(response);
    }
}
