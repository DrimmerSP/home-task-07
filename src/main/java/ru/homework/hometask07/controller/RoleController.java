package ru.homework.hometask07.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.dao.RoleRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.RoleEntity;

import java.util.List;

@RestController
@RequestMapping("/role")
@Tag(name = "Жанр", description = "Описание жанра фильма: ")
@RequiredArgsConstructor
public class RoleController {
    private final RoleRepository roleRepository;

    @Operation(description = "Получить список всех жанров.")
    @GetMapping
    public List<RoleEntity> getAllRoles(){
        return roleRepository.findAll();
    }

    @Operation(description = "Получить жанр по ID.")
    @GetMapping("/{id}")
    public RoleEntity getRolesByID(@PathVariable Integer id){
        return roleRepository.findById(id).orElse(null);
    }

    @Operation(description = "Добавить жанр.")
    @PostMapping
    public RoleEntity createRole(@RequestBody RoleEntity body){
        return roleRepository.save(body);
    }

    @Operation(description = "Обновить жанр.")
    @PutMapping("/{id}")
    public RoleEntity updateRole(@PathVariable Integer id, @RequestBody RoleEntity body){
        body.setId(id);
        return roleRepository.save(body);
    }

    @Operation(description = "Удалить жанр.")
    @DeleteMapping("/{id}")
    public void deleteRoleByID(@PathVariable Integer id){
        roleRepository.deleteById(id);
    }
}
