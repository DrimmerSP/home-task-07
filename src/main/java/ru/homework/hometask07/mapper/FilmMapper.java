package ru.homework.hometask07.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.FilmDto;
import ru.homework.hometask07.dao.DirectorRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.FilmEntity;

@Component
@RequiredArgsConstructor
public class FilmMapper {
    //TODO доделать мапперы
    private final DirectorRepository directorRepository;

    public static FilmDto entityToDto(FilmEntity entity) {
        return new FilmDto(entity.getId(),
                entity.getTitle(),
                entity.getPremierDate(),
                entity.getCountry(),
                entity.getGenre(),
                entity.getDirectors().stream()
                        .map(DirectorEntity::getId)
                        .toList()
        );
    }

    public FilmEntity dtoToEntity(FilmDto dto) {
        return FilmEntity.;  //TODO продолжить Маппер
    }
}
