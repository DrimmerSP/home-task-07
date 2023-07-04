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
            "/films",
            "/films/{id}",
            "/films/view",
            "/films/view/search",
            "/films/view/{id}");

    List<String> FILMS_PERMISSION_LIST = List.of(
            "/films/add",
            "/films/view/add",
            "/films/update",
            "/films/delete");

    List<String> DIRECTORS_WHITE_LIST = List.of(
            "/directors",
            "/directors/search",
            "/films/search/directors",
            "/directors/{id}"
    );

    List<String> DIRECTORS_PERMISSION_LIST = List.of(
            "/directors/add",
            "/directors/update",
            "/directors/delete");

    List<String> USERS_WHITE_LIST = List.of(
            "/login",
            "/users/registration",
            "/users/remember-password",
            "/users/change-password");

    List<String> USERS_PERMISSION_LIST = List.of("/rent/film/*");

    List<String> USERS_REST_WHITE_LIST = List.of("/users/auth");
}

