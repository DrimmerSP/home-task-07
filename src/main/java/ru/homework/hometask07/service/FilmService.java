package ru.homework.hometask07.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.controller.dto.FilmDto;
import ru.homework.hometask07.controller.dto.FilmSearchDto;
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

    public Page<FilmDto> searchFilm(FilmSearchDto filmSearchDTO,
                                    Pageable pageRequest) {

        String genre = filmSearchDTO.getGenre() != null
                ? String.valueOf(filmSearchDTO.getGenre().ordinal())
                : null;

        Page<FilmEntity> filmsPaginated = ((FilmRepository) repository).searchFilms(
                filmSearchDTO.getFilmTitle(),
                genre,
                filmSearchDTO.getDirectorFIO(),
                pageRequest
        );

        List<FilmDto> result = mapper.toDtos(filmsPaginated.getContent());
        return new PageImpl<>(result, pageRequest, filmsPaginated.getTotalElements());

    }
}