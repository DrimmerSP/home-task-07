package ru.homework.hometask07.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.homework.hometask07.dao.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
//    UserRepository userRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserByID() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserByID() {
    }

    @Test
    void getUserByLogin() {
      /*  UserEntity incomingUser = UserEntity.builder()
                .login("TestUser")
                .email("testMail@mailito.ru")
                .password("test")
                .build();
        UserEntity exp = userService.createUser(incomingUser);*/

        UserEntity actual = userService.getUserByLogin("TestUser");

        assertNull(actual);
//        assertNotNull(actual);
//        assertEquals(exp, actual);

    }
}