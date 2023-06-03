package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.homework.hometask07.dao.FilmRepository;
import ru.homework.hometask07.dao.entity.FilmEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    public final FilmRepository filmRepository;

    public List<FilmEntity> getAllFilm() {
        return filmRepository.findAll();
    }

    public FilmEntity getFilmByID(@PathVariable Integer id) {
        return filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Фильм ID: %s не найден.".formatted(id)));
    }

    public FilmEntity filmPost(@RequestBody FilmEntity body) {
        return filmRepository.save(body);
    }

    public FilmEntity updateFilm(@PathVariable Integer id, @RequestBody FilmEntity body) {
        body.setId(id);
        return filmRepository.save(body);
    }

    public void deleteFilmByID(@PathVariable Integer id) {
        filmRepository.deleteById(id);
    }
}
