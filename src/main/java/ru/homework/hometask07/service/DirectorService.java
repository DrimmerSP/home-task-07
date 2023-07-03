package ru.homework.hometask07.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.controller.dto.DirectorDto;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.GenericRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.mapper.GenericMapper;

@Service
public class DirectorService extends GenericService<DirectorEntity, DirectorDto> {
    private final DirectorRepository directorRepository;
    private final FilmService filmService;

    public DirectorService(GenericRepository<DirectorEntity> repository,
                           GenericMapper<DirectorEntity, DirectorDto> mapper,
                           DirectorRepository directorRepository,
                           FilmService filmService) {
        super(repository, mapper);
        this.directorRepository = directorRepository;
        this.filmService = filmService;
    }

//    public List<DirectorEntity> listAll() {
//        return directorRepository.findAll();
//    }

    public Page<DirectorEntity> getAllDirectors(PageRequest pageRequest) {  //добавил для вывода страницы
        return directorRepository.findAll(pageRequest);
    }

    public DirectorEntity getDirectorByID(Long id) {
        return directorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Режиссёр ID: %s не найден.".formatted(id)));
    }

//    public DirectorEntity create(DirectorEntity body) {
//        return directorRepository.save(body);
//    }

//    public DirectorEntity update(Long id, DirectorEntity body) {
//        body.setId(id);
//        return directorRepository.save(body);
//    }

//    public void delete(Long id) {
//        directorRepository.deleteById(id);
//    }

    public DirectorEntity addDirectorToFilm(Long filmId, Long directorId) {
        FilmEntity filmEntity = filmService.getOne(filmId);
        DirectorEntity directorEntity = getDirectorByID(directorId);
        directorEntity.getFilms().add(filmEntity);
        return directorRepository.save(directorEntity);
    }
}
