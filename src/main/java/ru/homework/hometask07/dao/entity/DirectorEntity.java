package ru.homework.hometask07.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "directors")
@SequenceGenerator(name = "default_generator", sequenceName = "users_seq", allocationSize = 1)
public class DirectorEntity extends GenericEntity {

    @Column(name = "director_fio")
    private String directorFIO;

    @Column(name = "position")
    private Integer position;

    @ManyToMany
    @JoinTable(
            name = "film_directors",
            joinColumns = @JoinColumn(name = "director_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<FilmEntity> films;


}
