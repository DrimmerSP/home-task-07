package ru.homework.hometask07.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.UserDto;
import ru.homework.hometask07.dao.RoleRepository;
import ru.homework.hometask07.dao.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleRepository roleRepository;

    public UserDto entityToDto(UserEntity entity) {
        return new UserDto(
                entity.getId(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getMiddleName(),
                entity.getBirthDate(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getCreatedWhen(),
                entity.getRole().getId()
        );
    }

    public UserEntity dtoToEntity(UserDto dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .middleName(dto.getMiddleName())
                .birthDate(dto.getBirthDate())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .createdWhen(dto.getCreatedWhen())
                .role(roleRepository.findById(dto.getRoleID()).orElse(null))
                .build();
    }
}
