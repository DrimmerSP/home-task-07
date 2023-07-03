package ru.homework.hometask07.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.homework.hometask07.controller.dto.GenericDto;
import ru.homework.hometask07.dao.GenericRepository;
import ru.homework.hometask07.dao.entity.GenericEntity;
import ru.homework.hometask07.mapper.GenericMapper;

import java.util.List;

/**
 * Абстрактный сервис который хранит в себе реализацию CRUD операций по умолчанию
 * Если реализация отличная от того что представлено в этом классе,
 * то она переопределяется в сервисе для конкретной сущности
 *
 * @param <E> - Сущность с которой мы работаем
 * @param <D> - DTO, которую мы будем отдавать/принимать дальше
 */
@Service
public abstract class GenericService<E extends GenericEntity, D extends GenericDto> {

    protected final GenericRepository<E> repository;
    protected final GenericMapper<E, D> mapper;

    public GenericService(GenericRepository<E> repository, GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<E> listAll() {
        return repository.findAll();
    }

    public Page<D> listAll(Pageable pageable) {
        Page<E> objects = repository.findAll(pageable);
        List<D> result = mapper.toDtos(objects.getContent());
        return new PageImpl<>(result, pageable, objects.getTotalElements());
    }

    public E getOne(final Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Данных по заданному id: %s не найдено!".formatted(id)));
    }

    public E create(E newObject) {
        return repository.save(newObject);
    }

    public E update(E updatedObject) {
        return repository.save(updatedObject);
    }

    public E update(Long id, E updateObject) {
        updateObject.setId(id);
        return repository.save(updateObject);
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }
}
