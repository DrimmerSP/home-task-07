package ru.homework.hometask07.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.RoleDto;
import ru.homework.hometask07.dao.entity.RoleEntity;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    public RoleDto entityToDto(RoleEntity entity) {
        return new RoleDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }

    public RoleEntity dtoToDto(RoleEntity dto) {
        return RoleEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }
}
