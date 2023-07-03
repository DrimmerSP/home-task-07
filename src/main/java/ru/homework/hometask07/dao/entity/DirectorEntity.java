package ru.homework.hometask07.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DirectorEntity that)) return false;
        if (!super.equals(o)) return false;

        if (!Objects.equals(directorFIO, that.directorFIO)) return false;
        if (!Objects.equals(position, that.position)) return false;
        return Objects.equals(films, that.films);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (directorFIO != null ? directorFIO.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        return result;
    }
}
