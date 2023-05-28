package ru.homework.hometask07.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.FilmEntity;

import java.util.List;

@RestController
@RequestMapping("/directors")
@Tag(name = "Продюссер", description = "Автор или авторы фильма.")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorRepository directorRepository;

    @Operation(description = "Получить список всех продюссеров.")
    @GetMapping
    public List<DirectorEntity> getAllDirectors(){
        return directorRepository.findAll();
    }

    @Operation(description = "Полудчить имя продюссера по ID.")
    @GetMapping("/{id}")
    public DirectorEntity getDirectorByID(@PathVariable Integer id){
        return directorRepository.findById(id).orElse(null);
    }

    @Operation(description = "Добавить продюссера.")
    @PostMapping
    public DirectorEntity createDirector(@RequestBody DirectorEntity body){
        return directorRepository.save(body);
    }

    @Operation(description = "Обновить данные о продюссере.")
    @PutMapping("/{id}")
    public DirectorEntity updateDirector(@PathVariable Integer id, @RequestBody DirectorEntity body){
        body.setId(id);
        return directorRepository.save(body);
    }


    @Operation(description = "Удалить запись о продюссере.")
    @DeleteMapping("/{id}")
    public void deleteDirectorByID(@PathVariable Integer id){
        directorRepository.deleteById(id);
    }

}
