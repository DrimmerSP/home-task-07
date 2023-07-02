package ru.homework.hometask07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.FilmRepository;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.mapper.FilmMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;

    protected FilmService(FilmRepository filmRepository,
                          FilmMapper filmMapper,
                          DirectorRepository directorRepository) {
        super(filmRepository, filmMapper);
        this.directorRepository = directorRepository;
    }

    public List<FilmEntity> getAllFilm(Pageable pageable) {
        Page<FilmEntity> filmEntityPage = filmRepository.findAll(pageable);
//        List<FilmDto> result = mapper.
        return filmRepository.findAll();
    }

    public Page<FilmEntity> getAllFilm(PageRequest pageRequest) {  //добавил для вывода страницы
        return filmRepository.findAll(pageRequest);
    }

    public FilmEntity getFilmByID(Integer id) {
        return filmRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Фильм ID: %s не найден.".formatted(id)));
    }

    public FilmEntity filmPost(FilmEntity body) {
        return filmRepository.save(body);
    }

    public FilmEntity updateFilm(Integer id, FilmEntity body) {
        body.setId(id);
        return filmRepository.save(body);
    }

    public void deleteFilmByID(Integer id) {
        filmRepository.deleteById(id);
    }
}
