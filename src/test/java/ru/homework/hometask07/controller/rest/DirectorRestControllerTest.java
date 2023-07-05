package ru.homework.hometask07.controller.rest;

/*import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;*/

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.homework.hometask07.controller.dto.DirectorDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Profile("dev")
class DirectorRestControllerTest extends CommonTestREST {
    private static Long createTestDirectorId;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
//    @Order(1)
    @DisplayName("Тест по просмотру всех режиссёров через REST")
    void getAllDirectors() throws Exception {
        log.info("Тест по просмотру всех режиссёров через REST начат");
        String result = mvc.perform(
                        MockMvcRequestBuilders.get("/directors")
                                .headers(super.headers)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
//              .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<DirectorDto> directorDtoList = objectMapper
                .readValue(result, new TypeReference<List<DirectorDto>>() {
                });
        directorDtoList.forEach(directorDto -> log.info(directorDto.toString()));
        log.info("Тест по просмотру всех режиссёров через REST закончен");
    }

    @Test
//    @Order(2)
    @DisplayName("Тест запроса режиссёра по ID, через REST")
    void getDirectorByID() throws Exception {
        log.info("Тест запроса режиссёра по ID, через REST - начат");
        String result = mvc.perform(
                        MockMvcRequestBuilders.get("/directors/2")
                                .headers(super.headers)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info("Тест запроса режиссёра по ID, через REST - закончен");
    }

    @Test
//    @Order(0)
    @DisplayName("Тест добавления режиссёра через REST")
    void createDirector() throws Exception {                        //TODO
        log.info("Тест по добавлению режиссёра через REST начат");
        DirectorDto directorDto = new DirectorDto("REST_TestDirectorFIO", 4, new ArrayList<>());

        DirectorDto result = objectMapper.readValue(
                mvc.perform(
                                MockMvcRequestBuilders.post("/directors") //, directorDto
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .headers(super.headers)
                                        .content(asJsonString(directorDto))
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().is2xxSuccessful())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                DirectorDto.class
        );
        createTestDirectorId = result.getId();
        log.info("{}", createTestDirectorId);
        log.info("Тест по добавлению режиссёра через REST закончен");
    }

    @Test
//    @Order(5)
    @DisplayName("Тест по обновлению режиссёра через REST")
    void updateDirector() throws Exception {
        log.info("Тест по обновлению режиссёра через REST начат");
        DirectorDto existingTestDirector = objectMapper.readValue(
                mvc.perform(
                                MockMvcRequestBuilders.get("/directors/2")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .headers(super.headers)
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().is2xxSuccessful())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                DirectorDto.class
        );

        existingTestDirector.setDirectorFIO("REST_TestDirectorFIO_UPDATED");

        mvc.perform(
                        MockMvcRequestBuilders.put("/directors/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(super.headers)
                                .content(asJsonString(existingTestDirector))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
//    @Order(6)
    @DisplayName("Тест удаления режиссёра по ID через REST")
    void deleteDirectorByID() throws Exception {
        log.info("Тест удаления режиссёра по ID через REST - начат");
        DirectorDto existingTestDirector = objectMapper.readValue(
                mvc.perform(
                                MockMvcRequestBuilders.get("/directors/2")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .headers(super.headers)
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().is2xxSuccessful())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                DirectorDto.class
        );
        assertTrue(existingTestDirector.isDeleted());

        mvc.perform(
                        MockMvcRequestBuilders.delete("/directors/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(super.headers)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        log.info("Данные очищены!");

        existingTestDirector = objectMapper.readValue(
                mvc.perform(
                                MockMvcRequestBuilders.get("/directors/2")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .headers(super.headers)
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().is2xxSuccessful())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                DirectorDto.class
        );
        assertTrue(existingTestDirector.isDeleted());
        log.info("Тест удаления режиссёра по ID через REST - закончен");
    }

    @Test
//    @Order(3)
    @DisplayName("Тест добавления режиссёра к фильму через REST")
    void addFilmToDirector() throws Exception {
        log.info("Тест добавления режиссёра к фильму через REST - начат");
        mvc.perform(
                        MockMvcRequestBuilders.put("/directors/2/addfilm")
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(super.headers)
                                .accept(MediaType.APPLICATION_JSON)
                                .param("filmId", String.valueOf(1L))
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());

        log.info("Тест добавления режиссёра к фильму через REST - закончен");
    }

    @Test
//    @Order(4)
    @DisplayName("Тест добавления фильма к режиссёру через REST")
    void addFilm() throws Exception {
        log.info("Тест добавления фильма к режиссёру через REST - начат");
        mvc.perform(
                        MockMvcRequestBuilders.post("/directors/addFilm")
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(super.headers)
                                .accept(MediaType.APPLICATION_JSON)
                                .param("filmId", String.valueOf(2L))
                                .param("directorId", String.valueOf(1L))
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        log.info("Тест добавления фильма к режиссёру через REST - закончен");


    }

    @Override
    protected void createObject() throws Exception {

    }

    @Override
    protected void updateObject() throws Exception {

    }

    @Override
    protected void deleteObject() throws Exception {

    }

    @Override
    protected void listAll() throws Exception {

    }
}