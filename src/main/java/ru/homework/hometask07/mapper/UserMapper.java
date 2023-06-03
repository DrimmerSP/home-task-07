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
                .id(dto.id())
                .login(dto.login())
                .password(dto.password())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .middleName(dto.middleName())
                .birthDate(dto.birthDate())
                .phone(dto.phone())
                .address(dto.address())
                .email(dto.email())
                .createdWhen(dto.createdWhen())
                .role(roleRepository.findById(dto.roleID()).orElse(null))
                .build();
    }
}
