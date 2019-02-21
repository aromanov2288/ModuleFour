package ru.romanov.modulefour.repository;

import org.springframework.data.repository.CrudRepository;
import ru.romanov.modulefour.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book save(Book book);

    Optional<Book> findById(Long Id);

    List<Book> findAll();

    void deleteById(Long id);
}
