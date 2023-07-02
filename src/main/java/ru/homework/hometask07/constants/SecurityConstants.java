package ru.homework.hometask07.constants;


import java.util.List;

public interface SecurityConstants {

    List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/static/**",
            "/js/**",
            "/css/**",
            "/",
            "/swagger-ui/**",
            "/v3/api-docs/**");

    List<String> FILMS_WHITE_LIST = List.of(
            "/films");
    List<String> BOOKS_PERMISSION_LIST = List.of(
            "/films/add",
            "/films/update",
            "/films/delete");

    List<String> USERS_WHITE_LIST = List.of(
            "/login",
            "/users/registration",
            "/users/remember-password");

    List<String> USERS_REST_WHITE_LIST = List.of("/users/login");  // /auth ?
}

