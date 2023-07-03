package ru.homework.hometask07.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.UserDto;
import ru.homework.hometask07.dao.RoleRepository;
import ru.homework.hometask07.dao.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class UserMapper extends GenericMapper<UserEntity, UserDto> {
    private final RoleRepository roleRepository;

    @Override
    public UserEntity toEntity(UserDto dto) {
        UserEntity result = new UserEntity();
        result.setId(dto.getId());
        result.setLogin(dto.getLogin());
        result.setPassword(dto.getPassword());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setMiddleName(dto.getMiddleName());
        result.setBirthDate(dto.getBirthDate());
        result.setPhone(dto.getPhone());
        result.setAddress(dto.getAddress());
        result.setEmail(dto.getEmail());
        result.setCreatedWhen(dto.getCreatedWhen());
        result.setCreatedBy(dto.getCreatedBy());
        result.setDeletedWhen(dto.getDeletedWhen());
        result.setDeletedBy(dto.getDeletedBy());
        result.setDeleted(dto.isDeleted());
        result.setRole(roleRepository.findById(dto.getRoleID()).orElse(null));
        return result;
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        UserDto result = new UserDto();
        result.setId(entity.getId());
        result.setLogin(entity.getLogin());
        result.setPassword(entity.getPassword());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setMiddleName(entity.getMiddleName());
        result.setBirthDate(entity.getBirthDate());
        result.setPhone(entity.getPhone());
        result.setAddress(entity.getAddress());
        result.setEmail(entity.getEmail());
        result.setCreatedWhen(entity.getCreatedWhen());
        result.setCreatedBy(entity.getCreatedBy());
        result.setDeletedWhen(entity.getDeletedWhen());
        result.setDeletedBy(entity.getDeletedBy());
        result.setDeleted(entity.isDeleted());
        result.setRoleID(entity.getRole().getId());
        return result;
    }
}
