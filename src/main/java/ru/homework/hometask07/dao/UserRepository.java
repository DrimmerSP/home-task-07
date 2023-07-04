package ru.homework.hometask07.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.homework.hometask07.dao.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<UserEntity> {

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByEmail(String email);
}
