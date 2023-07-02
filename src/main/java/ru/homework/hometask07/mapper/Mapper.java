package ru.homework.hometask07.mapper;

import ru.homework.hometask07.controller.dto.GenericDto;
import ru.homework.hometask07.dao.entity.GenericEntity;

import java.util.List;

public interface Mapper <E extends GenericEntity, D extends GenericDto>{
    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDTOs(List<E> entities);
}
