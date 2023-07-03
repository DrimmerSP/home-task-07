package ru.homework.hometask07.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.RoleDto;
import ru.homework.hometask07.dao.entity.RoleEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    public RoleDto toDto(RoleEntity entity) {
        return new RoleDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }

    public RoleEntity toEntity(RoleDto dto) {
        return new RoleEntity(
                dto.getId(),
                dto.getTitle(),
                dto.getDescription()
        );
    }

    public List<RoleDto> toDtos(List<RoleEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<RoleEntity> toEntities(List<RoleDto> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
