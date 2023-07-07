package ru.homework.hometask07.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.hometask07.controller.dto.DirectorDto;
import ru.homework.hometask07.dao.FilmRepository;
import ru.homework.hometask07.dao.entity.DirectorEntity;
import ru.homework.hometask07.dao.entity.FilmEntity;
import ru.homework.hometask07.dao.entity.GenericEntity;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DirectorMapper extends GenericMapper<DirectorEntity, DirectorDto> {
    private final FilmRepository filmRepository;

    @Override
    public DirectorEntity toEntity(DirectorDto dto) {
        DirectorEntity result = new DirectorEntity();
        result.setId(dto.getId());
        result.setDirectorFIO(dto.getDirectorFIO());
        result.setPosition(dto.getPosition());
        result.setDescription(dto.getDescription());
        result.setCreatedWhen(dto.getCreatedWhen());
        result.setCreatedBy(dto.getCreatedBy());
        result.setDeletedWhen(dto.getDeletedWhen());
        result.setDeletedBy(dto.getDeletedBy());
        result.setDeleted(dto.isDeleted());
        result.setFilms(dto.getFilms().keySet().stream()
                .map(s -> filmRepository.findById(s).orElse(null))
                .collect(Collectors.toList()));
        return result;
    }

    @Override
    public DirectorDto toDto(DirectorEntity entity) {
        DirectorDto result = new DirectorDto();
        result.setId(entity.getId());
        result.setDirectorFIO(entity.getDirectorFIO());
        result.setPosition(entity.getPosition());
        result.setDescription(entity.getDescription());
        result.setCreatedWhen(entity.getCreatedWhen());
        result.setCreatedBy(entity.getCreatedBy());
        result.setDeletedWhen(entity.getDeletedWhen());
        result.setDeletedBy(entity.getDeletedBy());
        result.setDeleted(entity.isDeleted());
        result.setFilms(entity.getFilms().stream()
                .collect(Collectors.toMap(GenericEntity::getId, FilmEntity::getTitle)));
        return result;
    }
}
