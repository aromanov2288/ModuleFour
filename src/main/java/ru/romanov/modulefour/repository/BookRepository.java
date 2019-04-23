package ru.romanov.modulefour.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.romanov.modulefour.domain.Book;
import ru.romanov.modulefour.domain.Genre;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    @Query(value = "{}", fields = "{'genre': 1}")
    Flux<Genre> findAllGenres();

    Flux<Book> findAllByGenreLike(String genre);
}
