package ru.homework.hometask07.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class DirectorController {
    private final DirectorService directorService;
    private final DirectorMapper directorMapper;
    private final UpdateFilmDirectorService updateFilmDirectorService;

    @Operation(description = "Получить список всех продюссеров.")
    @GetMapping
    public List<DirectorDto> getAllDirectors() {
        return directorMapper.toDtos(directorService.listAll());
    }

    @Operation(description = "Полудчить имя продюссера по ID.")
    @GetMapping("/{id}")
    public DirectorDto getDirectorByID(@PathVariable Long id) {
        return directorMapper.toDto(directorService.getDirectorByID(id));
    }

    @Operation(description = "Добавить продюссера.")
    @PostMapping
    public DirectorDto createDirector(@RequestBody DirectorDto body) {
        return directorMapper.toDto(directorService.create(directorMapper.toEntity(body)));
    }

    @Operation(description = "Обновить данные о продюссере.")
    @PutMapping("/{id}")
    public DirectorDto updateDirector(@PathVariable Long id, @RequestBody DirectorDto body) {
        return directorMapper.toDto(directorService.update(id, directorMapper.toEntity(body)));
    }

    @Operation(description = "Удалить запись о продюссере.")
    @DeleteMapping("/{id}")
    public void deleteDirectorByID(@PathVariable Long id) {
        directorService.deleteSoft(id);
    }

    @PutMapping("/{id}/addfilm")
    public void addFilmToDirector(@PathVariable(value = "id") Long directorId, @RequestParam Long filmId) {
        updateFilmDirectorService.updateFilmDirector(directorId, filmId);
    }

    @Operation(description = "Добавить фильм к продюссеру")
    @RequestMapping(value = "/addFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDto> addFilm(@RequestParam(value = "filmId") Long filmId,
                                               @RequestParam(value = "directorId") Long directorId) {
        log.info("Вызван метод Добавить фильм к продюссеру с параметрами FilmId: {}, DirectorId: {}", filmId, directorId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(directorMapper.toDto(directorService.addDirectorToFilm(filmId, directorId)));
    }
}
