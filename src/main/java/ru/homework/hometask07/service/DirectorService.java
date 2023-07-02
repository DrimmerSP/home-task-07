package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.FilmEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final FilmService filmService;

    public List<DirectorEntity> getAllDirectors() {
        return directorRepository.findAll();
    }

    public Page<DirectorEntity> getAllDirectors(PageRequest pageRequest) {  //добавил для вывода страницы
        return directorRepository.findAll(pageRequest);
    }

    public DirectorEntity getDirectorByID(Integer id) {
        return directorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Режиссёр ID: %s не найден.".formatted(id)));
    }

    public DirectorEntity createDirector(DirectorEntity body) {
        return directorRepository.save(body);
    }

    public DirectorEntity updateDirector(Integer id, DirectorEntity body) {
        body.setId(id);
        return directorRepository.save(body);
    }

    public void deleteDirectorByID(Integer id) {
        directorRepository.deleteById(id);
    }

    public DirectorEntity filmPost(Integer filmId, Integer directorId) {
        FilmEntity filmEntity = filmService.getFilmByID(filmId);
        DirectorEntity directorEntity = getDirectorByID(directorId);
        directorEntity.getFilms().add(filmEntity);
        return directorRepository.save(directorEntity);
    }
}
