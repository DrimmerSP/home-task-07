package ru.homework.hometask07.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {
//    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}