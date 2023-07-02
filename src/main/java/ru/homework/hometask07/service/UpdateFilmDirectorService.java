package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.FilmRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.FilmEntity;

@Service
@RequiredArgsConstructor
public class UpdateFilmDirectorService {
    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    public void updateFilmDirector(Long filmId, Long directorId) {
        FilmEntity filmEntity = filmRepository.findById(filmId).orElseThrow(() ->
                new RuntimeException("Фильм ID: %s не найден.".formatted(filmId)));
        DirectorEntity directorEntity = directorRepository.findById(directorId).orElseThrow(() ->
                new RuntimeException("Режиссёр ID: %s не найден.".formatted(directorId)));

        filmEntity.getDirectors().add(directorEntity);
        directorEntity.getFilms().add(filmEntity);

        filmRepository.save(filmEntity);
        directorRepository.save(directorEntity);
    }
}
