package ru.homework.hometask07.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.homework.hometask07.controller.dto.FilmDto;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.mapper.FilmMapper;
import ru.homework.hometask07.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
@Tag(name = "Фильм", description = "Название фильма: ")
@RequiredArgsConstructor
public class FilmController {
    //    private final FilmRepository filmRepository;
    private final FilmService filmService;
    private final FilmMapper filmMapper;

    @Operation(description = "Получить список всех фильмов.")
    @GetMapping
    public List<FilmDto> getAllFilm() {
        return filmService.getAllFilm().stream()
                .map(filmMapper::entityToDto)
                .toList();
    }

    @Operation(description = "Получить название фильма по ID.")
    @GetMapping("/{id}")
    public FilmEntity getFilmByID(@PathVariable Integer id) {
        return filmRepository.findById(id).orElse(null);
        //TODO продолжить создавать сервисы и изменять в контроллерах
    }

    @Operation(description = "Разместить фильм в базу данных.")
    @PostMapping
    public FilmEntity filmPost(@RequestBody FilmEntity body) {
        return filmRepository.save(body);
    }

    @Operation(description = "Обновить информацию о фильме.")
    @PutMapping("/{id}")
    public FilmEntity updateFilm(@PathVariable Integer id, @RequestBody FilmEntity body) {
        body.setId(id);
        return filmRepository.save(body);
    }

    @Operation(description = "Удалить запись о фильме.")
    @DeleteMapping("/{id}")
    public void deleteFilmByID(@PathVariable Integer id) {
        filmRepository.deleteById(id);
    }
}
