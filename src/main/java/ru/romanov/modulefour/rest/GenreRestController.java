package ru.romanov.modulefour.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.romanov.modulefour.domain.Book;
import ru.romanov.modulefour.domain.Genre;
import ru.romanov.modulefour.form.UpdatedGenre;
import ru.romanov.modulefour.repository.BookRepository;

@RestController
@RequestMapping(value = {"/api/genres"})
public class GenreRestController {

    private BookRepository bookRepository;

    @Autowired
    public GenreRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Flux<Genre> getAll() {
        return bookRepository.findAllGenres().distinct();
    }

    @GetMapping(value = "{name}")
    public Mono<Genre> getGenreById(@PathVariable(value = "name") String name) {
        return Mono.just(new Genre(name));
    }

    @PutMapping
    public Mono<Void> updateGenre(@RequestBody UpdatedGenre updatedGenre) {
        String editName = updatedGenre.getEditName();
        String  name = updatedGenre.getName();
        return bookRepository.findAllByGenreLike(editName).map(book -> {
            book.setGenre(name);
            return book;
        }).flatMap(book -> bookRepository.save(book)).then();
    }

    @DeleteMapping(value = "{genre}")
    public Mono<Void> deleteGenreById(@PathVariable(value = "genre") String genre) {
        return bookRepository.deleteAll(bookRepository.findAllByGenreLike(genre));
    }
}
