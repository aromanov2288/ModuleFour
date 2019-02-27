package ru.romanov.modulefour.repository;

import org.springframework.data.repository.CrudRepository;
import ru.romanov.modulefour.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Genre save(Genre genre);

    Optional<Genre> findById(Long Id);

    List<Genre> findAll();

    void deleteById(Long id);
}
