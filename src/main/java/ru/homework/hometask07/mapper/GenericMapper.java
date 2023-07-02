package ru.homework.hometask07.mapper;

import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.GenericDto;
import ru.homework.hometask07.dao.entity.GenericEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Абстрактный маппер, который объявляет основные операции конвертации ИЗ СУЩНОСТИ В ДТО
 * и обратно. С помощью этого класса мы фиксируем основные методы по работе с маппером.
 *
 * @param <E> - Сущность с которой мы работаем
 * @param <D> - DTO, которую мы будем отдавать/принимать дальше
 */
@Component
public abstract class GenericMapper<E extends GenericEntity, D extends GenericDto> implements Mapper<E, D> {

    @Override
    public abstract E toEntity(D dto);

    @Override
    public abstract D toDTO(E entity);

    @Override
    public List<E> toEntities(List<D> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<D> toDTOs(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
