package ru.homework.hometask07.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.homework.hometask07.constants.MailConstants;
import ru.homework.hometask07.controller.dto.UserDto;
import ru.homework.hometask07.dao.UserRepository;
import ru.homework.hometask07.dao.entity.UserEntity;
import ru.homework.hometask07.mapper.UserMapper;
import ru.homework.hometask07.utils.MailUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class UserService extends GenericService<UserEntity, UserDto> {
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;

    public UserService(UserRepository repository,
                       UserMapper mapper,
                       RoleService roleService,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       JavaMailSender javaMailSender) {
        super(repository, mapper);
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public UserEntity create(UserEntity body) {
        body.setRole(roleService.getRoleByID(3L));
        body.setPassword(bCryptPasswordEncoder.encode(body.getPassword()));
        body.setCreatedWhen(LocalDateTime.now());
        return repository.save(body);
    }

    public UserEntity getUserByLogin(final String login) {
        return ((UserRepository) repository).findByLogin(login).orElse(null);
    }

    public UserEntity getUserByEmail(final String email) {
        return ((UserRepository) repository).findByEmail(email).orElse(null);
    }

    public boolean checkPassword(String password, UserDetails foundUser) {
        return bCryptPasswordEncoder.matches(password, foundUser.getPassword());
    }

    public void changePassword(String uuid, String password) {
        UserEntity userEntity = ((UserRepository) repository).findUserByChangePasswordToken(uuid);
        userEntity.setChangePasswordToken(null);
        userEntity.setPassword(bCryptPasswordEncoder.encode(password));
        update(userEntity);
    }

    public void sendChangePasswordEmail(UserEntity userEntity) {
        UUID uuid = UUID.randomUUID();
        log.info("Генерация токена для смены пароля {}", uuid);
        userEntity.setChangePasswordToken(uuid.toString());
        update(userEntity);

        SimpleMailMessage mailMessage = MailUtils.createMailMessage(
                userEntity.getEmail(),
                "libraryproject@bk.ru",
                MailConstants.MAIL_SUBJECT_FOR_REMEMBER_PASSWORD,
                MailConstants.MAIL_MESSAGE_FOR_REMEMBER_PASSWORD + uuid
        );

        javaMailSender.send(mailMessage);
    }
}
