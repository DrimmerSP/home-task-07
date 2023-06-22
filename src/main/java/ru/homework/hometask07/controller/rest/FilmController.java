package ru.homework.hometask07.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.controller.dto.FilmDto;
import ru.homework.hometask07.mapper.FilmMapper;
import ru.homework.hometask07.service.FilmService;
import ru.homework.hometask07.service.UpdateFilmDirectorService;

import java.util.List;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильм", description = "Название фильма: ")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    private final FilmMapper filmMapper;
    private final UpdateFilmDirectorService updateFilmDirectorService;

    @Operation(description = "Получить список всех фильмов.")
    @GetMapping
    public List<FilmDto> getAllFilm() {
        return filmService.getAllFilm().stream()
                .map(filmMapper::entityToDto)
                .toList();
    }

    @Operation(description = "Получить название фильма по ID.")
    @GetMapping("/{id}")
    public FilmDto getFilmByID(@PathVariable Integer id) {
        return filmMapper.entityToDto(filmService.getFilmByID(id));
    }

    @Operation(description = "Разместить фильм в базу данных.")
    @PostMapping
    public FilmDto filmPost(@RequestBody FilmDto body) {
        return filmMapper.entityToDto(filmService.filmPost(filmMapper.dtoToEntity(body)));
    }

    @Operation(description = "Обновить информацию о фильме.")
    @PutMapping("/{id}")
    public FilmDto updateFilm(@PathVariable Integer id, @RequestBody FilmDto body) {
        return filmMapper.entityToDto(filmService.updateFilm(id, filmMapper.dtoToEntity(body)));
    }

    @Operation(description = "Удалить запись о фильме.")
    @DeleteMapping("/{id}")
    public void deleteFilmByID(@PathVariable Integer id) {
        filmService.deleteFilmByID(id);
    }

    @PutMapping("/{id}/adddirector")
    public void addDirectorToFilm(@PathVariable(value = "id") Integer filmId, @RequestParam Integer directorId) {
        updateFilmDirectorService.updateFilmDirector(filmId, directorId);
    }
}
