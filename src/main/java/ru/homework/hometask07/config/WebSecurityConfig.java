package ru.homework.hometask07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.homework.hometask07.service.userdetails.CustomUserDetailsService;

import static ru.homework.hometask07.constants.SecurityConstants.*;
import static ru.homework.hometask07.constants.UserRolesConstants.*;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class WebSecurityConfig {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }
/*    private final List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/static/**",
            "/js/**",
            "/css/**",
            "/",
            "swagger-ui/**");
    private final List<String> FILMS_WHITE_LIST = List.of("/films");

    private final List<String> FILMS_PERMISIONS_LIST = List.of(
            "/films/add",
            "films/update",
            "films/delete");

    private final List<String> USER_WHITE_LIST = List.of(
            "/login",
            "/users/registration",
            "/users/remember-password/");*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                //Настройка http-запросов - кому/куда можно/нельзя
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(FILMS_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(USERS_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(DIRECTORS_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(FILMS_PERMISSION_LIST.toArray(String[]::new)).hasAnyRole(ADMIN.name(), MANAGER.name())
                        .requestMatchers(DIRECTORS_PERMISSION_LIST.toArray(String[]::new)).hasAnyRole(ADMIN.name(), MANAGER.name())
                        .requestMatchers(USERS_PERMISSION_LIST.toArray(String[]::new)).hasAnyRole(USER.name(), MANAGER.name())
                        .anyRequest().authenticated()
                )
                //Настройка для входа в систему
                .formLogin((form) -> form
                        .loginPage("/login")
                        //Перенаправление на главную страницу после успеха
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                );

        return httpSecurity.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
