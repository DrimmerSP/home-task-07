package ru.homework.hometask07.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.controller.dto.FilmDto;
import ru.homework.hometask07.dao.FilmRepository;
import ru.homework.hometask07.dao.GenericRepository;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.mapper.GenericMapper;

import java.util.List;

@Service
public class FilmService extends GenericService<FilmEntity, FilmDto> {
    public final FilmRepository filmRepository;

    public FilmService(GenericRepository<FilmEntity> repository,
                       GenericMapper<FilmEntity, FilmDto> mapper,
                       FilmRepository filmRepository) {
        super(repository, mapper);
        this.filmRepository = filmRepository;
    }

    @Override
    public List<FilmEntity> listAll() {
        return filmRepository.findAll();
    }

    public Page<FilmEntity> listAll(PageRequest pageRequest) {  //добавил для вывода страницы
        return filmRepository.findAll(pageRequest);
    }

//    public FilmEntity update(Long id, FilmEntity body) {
//        body.setId(id);
//        return filmRepository.save(body);
//    }
}
