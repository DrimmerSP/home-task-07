package ru.homework.hometask07.controller.rest;

/*import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;*/

import org.junit.jupiter.api.*;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.homework.hometask07.controller.dto.DirectorDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    @Order(0)
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
    @Order(1)
    void getDirectorByID() {

    }

    @Test
    @DisplayName("Тест добавления режиссёра через REST")
    void createDirector() throws Exception {
        log.info("Тест по созданию режиссёра через REST начат");

        DirectorDto directorDto = new DirectorDto("REST_TestDirectorFIO", 4, new ArrayList<>());

        DirectorDto result = objectMapper.readValue(
                mvc.perform(
                                MockMvcRequestBuilders.post("/directors")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .headers(super.headers)
                                        .content(asJsonString(directorDto))
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().is2xxSuccessful())
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                DirectorDto.class
        );
        createTestDirectorId = result.getId();
        log.info("{}", createTestDirectorId);
        log.info("Тест по созданию режиссёра через REST закончен");
    }

    @Test
    @DisplayName("Тест по обновлению режиссёра через REST")
    void updateDirector() throws Exception {
        log.info("Тест по обновлению режиссёра через REST начат");
        DirectorDto existingTestDirector = objectMapper.readValue(
                mvc.perform(
                                MockMvcRequestBuilders.get("/directors/{id}")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .headers(super.headers)
                                        .param("id", String.valueOf(createTestDirectorId))
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().is2xxSuccessful())
//                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                DirectorDto.class
        );

        existingTestDirector.setDirectorFIO("REST_TestDirectorFIO_UPDATED");

        mvc.perform(
                        MockMvcRequestBuilders.put("/directors/{id}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(super.headers)
                                .content(asJsonString(existingTestDirector))
                                .param("id", String.valueOf(createTestDirectorId))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteDirectorByID() {
    }

    @Test
    void addFilmToDirector() {
    }

    @Test
    void addBook() {
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