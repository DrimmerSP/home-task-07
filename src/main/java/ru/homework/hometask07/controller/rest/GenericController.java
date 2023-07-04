package ru.homework.hometask07.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.controller.dto.GenericDto;
import ru.homework.hometask07.dao.entity.GenericEntity;
import ru.homework.hometask07.mapper.GenericMapper;
import ru.homework.hometask07.service.GenericService;

import java.util.List;

@RestController
@Slf4j
public abstract class GenericController<E extends GenericEntity, D extends GenericDto> {
    protected GenericService<E, D> service;
    protected final GenericMapper<E, D> mapper;

    public GenericController(GenericService<E, D> genericService, GenericMapper<E, D> mapper) {
        this.service = genericService;
        this.mapper = mapper;
    }

    @Operation(description = "Получить запись по ID", method = "getOneByID")
    @RequestMapping(value = "/getOneById",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> getOneById(@RequestParam(value = "id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDto(service.getOne(id)));
    }

    @Operation(description = "Получить все записи", method = "getAll")
    @RequestMapping(value = "/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<D>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDtos(service.listAll()));
    }

    @Operation(description = "Создать запись", method = "add")
    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> create(@RequestBody D newEntity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDto(service.create(mapper.toEntity(newEntity))));
    }


    @Operation(description = "Обновить запись", method = "update")
    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> update(@RequestBody D updatedEntity,
                                    @RequestParam(value = "id") Long id) {
        updatedEntity.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(mapper.toDto(service.update(mapper.toEntity(updatedEntity))));
    }
}
