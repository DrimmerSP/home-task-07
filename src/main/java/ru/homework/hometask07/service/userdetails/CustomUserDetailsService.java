package ru.homework.hometask07.service.userdetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.constants.UserRolesConstants;
import ru.homework.hometask07.dao.entity.UserEntity;
import ru.homework.hometask07.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUserName)) {
            return new CustomUserDetails(
                    adminPassword,
                    List.of(new SimpleGrantedAuthority("ROLE_" + UserRolesConstants.ADMIN)),
                    username,
                    null
            );
        } else {
            UserEntity user = userService.getUserByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();

            //ROLE_USER, ROLE_MANAGER
            authorities.add(new SimpleGrantedAuthority(user.getRole().getId() == 1
                    ? "ROLE_" + UserRolesConstants.USER
                    : "ROLE_" + UserRolesConstants.MANAGER));

            return new CustomUserDetails(user.getPassword(), authorities, username, user.getId());
        }
    }
}
