package ru.homework.hometask07.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.homework.hometask07.controller.dto.RoleDto;
import ru.homework.hometask07.dao.entity.RoleEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RoleMapperTest {
    @Autowired
    RoleMapper roleMapper;
    RoleEntity testEntity;
    RoleDto testDto;

    @BeforeEach
    void setUp() {
        testEntity = new RoleEntity()
                .setId(5L)
                .setTitle("Tesl Title")
                .setDescription("Test description");
        testDto = new RoleDto()
                .setId(5L)
                .setTitle("Tesl Title")
                .setDescription("Test description");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Mapping Entity to DTO")
    void toDto() {
        RoleEntity incoming = testEntity;
        RoleDto exp = testDto;

        RoleDto actual = roleMapper.toDto(incoming);

        assertNotNull(actual);
        assertEquals(exp, actual);
    }

    @Test
    @DisplayName("Mapping DTO to Entity")
    void toEntity() {
        RoleDto incoming = testDto;
        RoleEntity exp = testEntity;

        RoleEntity actual = roleMapper.toEntity(incoming);

        assertNotNull(actual);
        assertEquals(exp, actual);
    }

    @Test
    @DisplayName("Mapping toDTOs")
    void toDtos() {
        List<RoleEntity> incoming = List.of(testEntity);
        List<RoleDto> exp = List.of(testDto);

        List<RoleDto> actual = roleMapper.toDtos(incoming);

        assertNotNull(actual);
        assertEquals(exp, actual);
    }

    @Test
    @DisplayName("Mapping toEntities")
    void toEntities() {
        List<RoleDto> incoming = List.of(testDto);
        List<RoleEntity> exp = List.of(testEntity);

        List<RoleEntity> actual = roleMapper.toEntities(incoming);

        assertNotNull(actual);
        assertEquals(exp, actual);
    }
}