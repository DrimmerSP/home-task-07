package ru.homework.hometask07.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.controller.dto.DirectorDto;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.GenericRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.mapper.DirectorMapper;
import ru.homework.hometask07.mapper.GenericMapper;

@Service
public class DirectorService extends GenericService<DirectorEntity, DirectorDto> {
    private final FilmService filmService;

    public DirectorService(DirectorRepository repository,
                           DirectorMapper mapper,
                           FilmService filmService) {
        super(repository, mapper);
        this.filmService = filmService;
    }

    public Page<DirectorEntity> getAllDirectors(PageRequest pageRequest) {  //добавил для вывода страницы
        return repository.findAll(pageRequest);
    }

    public DirectorEntity getDirectorByID(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Режиссёр ID: %s не найден.".formatted(id)));
    }

    public DirectorEntity addDirectorToFilm(Long filmId, Long directorId) {
        FilmEntity filmEntity = filmService.getOne(filmId);
        DirectorEntity directorEntity = getDirectorByID(directorId);
        directorEntity.getFilms().add(filmEntity);
        return repository.save(directorEntity);
    }
}
