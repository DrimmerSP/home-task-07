package ru.homework.hometask07.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.homework.hometask07.controller.dto.DirectorDto;
import ru.homework.hometask07.dao.entity.DirectorEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DirectorMapperTest {
    @Autowired
    DirectorMapper directorMapper;
    DirectorEntity testEntity;
    DirectorDto testDto;

    @BeforeEach
    void setUp() {
        testEntity = new DirectorEntity();
        testEntity.setDirectorFIO("TestDirector S.V.");
        testEntity.setPosition(1);
        testEntity.setFilms(new ArrayList<>());
        testEntity.setId(8L);
        testEntity.setCreatedWhen(LocalDateTime.of(2020, 11, 15, 10, 30));
        testEntity.setCreatedBy("TestUser");
        testEntity.setCreatedWhen(LocalDateTime.of(2020, 11, 15, 10, 30));
        testEntity.setDeletedBy("TestAdmin");
        testEntity.setDeleted(true);

        testDto = new DirectorDto();
        testDto.setDirectorFIO("TestVasya S.G.");
        testDto.setPosition(5);
        testDto.setFilmIDs(new ArrayList<>());
        testDto.setId(8L);
        testDto.setCreatedWhen(LocalDateTime.of(2020, 11, 15, 10, 30));
        testDto.setCreatedBy("TestUser");
        testDto.setCreatedWhen(LocalDateTime.of(2020, 11, 15, 10, 30));
        testDto.setDeletedBy("TestAdmin");
        testDto.setDeleted(true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Mapping DTO to Entity")
    void toEntity() {
        DirectorDto incoming = testDto;
        DirectorEntity exp = testEntity;

        DirectorEntity actual = directorMapper.toEntity(incoming);

        assertNotNull(actual);
        assertEquals(exp, actual);
    }

    @Test
    @DisplayName("Mapping Entity to DTO")
    void toDto() {
    }
}