package ru.homework.hometask07.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.DirectorDto;
import ru.homework.hometask07.dao.FilmRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.FilmEntity;

@Component
@RequiredArgsConstructor
public class DirectorMapper {
    private final FilmRepository filmRepository;

    public DirectorDto entityToDto(DirectorEntity entity) {
        return new DirectorDto(
                entity.getId(),
                entity.getDirectorFIO(),
                entity.getPosition(),
                entity.getFilms().stream()
                        .map(FilmEntity::getId)
                        .toList()
        );
    }

    public DirectorEntity dtoToEntity(DirectorDto dto) {
        return new DirectorEntity(
                dto.id(),
                dto.directorFIO(),
                dto.position(),
                dto.filmIDs().stream()
                        .map(id -> filmRepository.findById(id).orElse(null))
                        .toList()
        );
    }
}
