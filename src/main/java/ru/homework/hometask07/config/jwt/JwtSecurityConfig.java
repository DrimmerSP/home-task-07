//package ru.homework.hometask07.config.jwt;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import ru.homework.hometask07.service.userdetails.CustomUserDetailsService;
//
//import static ru.homework.hometask07.constants.SecurityConstants.*;
//import static ru.homework.hometask07.constants.UserRolesConstants.ADMIN;
//import static ru.homework.hometask07.constants.UserRolesConstants.USER;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class JwtSecurityConfig {
//
//    private final CustomUserDetailsService customUserDetailsService;
//    private final JwtTokenFilter jwtTokenFilter;
//
//    public JwtSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
//                             CustomUserDetailsService customUserDetailsService,
//                             JwtTokenFilter jwtTokenFilter) {
//        this.customUserDetailsService = customUserDetailsService;
//        this.jwtTokenFilter = jwtTokenFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .cors().disable()
//                .csrf().disable()
//                //Настройка http-запросов - кому/куда можно/нельзя
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
//                        .requestMatchers(USERS_REST_WHITE_LIST.toArray(String[]::new)).permitAll()
//                        .requestMatchers(USERS_WHITE_LIST.toArray(String[]::new)).permitAll()
//                        .requestMatchers("/directors/**").hasAnyRole(ADMIN.name(), USER.name())
//                        .anyRequest().authenticated()
//                )
//                .exceptionHandling()
////                .authenticationEntryPoint()
//                .and()
//                .sessionManagement(
//                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                //JWT Token Filter VALID OR NOT
//                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .userDetailsService(customUserDetailsService);
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//}
//
