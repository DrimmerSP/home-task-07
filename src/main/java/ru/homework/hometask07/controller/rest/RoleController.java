package ru.homework.hometask07.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.controller.dto.RoleDto;
import ru.homework.hometask07.mapper.RoleMapper;
import ru.homework.hometask07.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/role")
@Tag(name = "Жанр", description = "Описание жанра фильма: ")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Operation(description = "Получить список всех жанров.")
    @GetMapping
    public List<RoleDto> getAllRoles() {
        return roleMapper.toDtos(roleService.getAllRoles());
    }

    @Operation(description = "Получить жанр по ID.")
    @GetMapping("/{id}")
    public RoleDto getRolesByID(@PathVariable Long id) {
        return roleMapper.toDto(roleService.getRoleByID(id));
    }

    @Operation(description = "Добавить жанр.")
    @PostMapping
    public RoleDto createRole(@RequestBody RoleDto body) {
        return roleMapper.toDto(roleService.createRole(roleMapper.toEntity(body)));
    }

    @Operation(description = "Обновить жанр.")
    @PutMapping("/{id}")
    public RoleDto updateRole(@PathVariable Long id, @RequestBody RoleDto body) {
        return roleMapper.toDto(roleService.updateRole(id, roleMapper.toEntity(body)));
    }

    @Operation(description = "Удалить жанр.")
    @DeleteMapping("/{id}")
    public void deleteRoleByID(@PathVariable Long id) {
        roleService.deleteRoleByID(id);
    }
}
