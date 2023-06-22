package ru.homework.hometask07.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.controller.dto.DirectorDto;
import ru.homework.hometask07.mapper.DirectorMapper;
import ru.homework.hometask07.service.DirectorService;
import ru.homework.hometask07.service.UpdateFilmDirectorService;

import java.util.List;

@RestController
@RequestMapping("/directors")
@Tag(name = "Продюссер", description = "Автор или авторы фильма.")
@RequiredArgsConstructor
public class DirectorController {
    //    private final DirectorRepository directorRepository;
    private final DirectorService directorService;
    private final DirectorMapper directorMapper;
    private final UpdateFilmDirectorService updateFilmDirectorService;

    @Operation(description = "Получить список всех продюссеров.")
    @GetMapping
    public List<DirectorDto> getAllDirectors() {
        return directorService.getAllDirectors().stream()
                .map(directorMapper::entityToDto)
                .toList();
    }

    @Operation(description = "Полудчить имя продюссера по ID.")
    @GetMapping("/{id}")
    public DirectorDto getDirectorByID(@PathVariable Integer id) {
        return directorMapper.entityToDto(directorService.getDirectorByID(id));
    }

    @Operation(description = "Добавить продюссера.")
    @PostMapping
    public DirectorDto createDirector(@RequestBody DirectorDto body) {
        return directorMapper.entityToDto(directorService.createDirector(directorMapper.dtoToEntity(body)));
    }

    @Operation(description = "Обновить данные о продюссере.")
    @PutMapping("/{id}")
    public DirectorDto updateDirector(@PathVariable Integer id, @RequestBody DirectorDto body) {
        return directorMapper.entityToDto(directorService.updateDirector(id, directorMapper.dtoToEntity(body)));
    }

    @Operation(description = "Удалить запись о продюссере.")
    @DeleteMapping("/{id}")
    public void deleteDirectorByID(@PathVariable Integer id) {
        directorService.deleteDirectorByID(id);
    }

    @PutMapping("/{id}/addfilm")
    public void addFilmToDirector(@PathVariable(value = "id") Integer directorId, @RequestParam Integer filmId) {
        updateFilmDirectorService.updateFilmDirector(directorId, filmId);
    }
}
