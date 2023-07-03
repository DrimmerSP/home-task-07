package ru.homework.hometask07.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(name = "uniqueEmail", columnNames = "email"),
                @UniqueConstraint(name = "uniqueLogin", columnNames = "login")
        })
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "users_seq", allocationSize = 1)
public class UserEntity extends GenericEntity {
/*    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;*/

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_USERS_ROLES"))
    private RoleEntity role;


}
