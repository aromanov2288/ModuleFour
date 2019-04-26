package ru.romanov.modulefour.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.romanov.modulefour.domain.Author;
import ru.romanov.modulefour.domain.Book;
import ru.romanov.modulefour.domain.Genre;
import ru.romanov.modulefour.form.NewBook;
import ru.romanov.modulefour.form.UpdatedBook;
import ru.romanov.modulefour.repository.BookRepository;

import javax.sound.midi.VoiceStatus;

@RestController
@RequestMapping(value = {"/api/books"})
public class BookRestController {

    private BookRepository bookRepository;

    @Autowired
    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Mono<Book> addBook(@RequestBody NewBook newBook) {
        String name = newBook.getName();
        String genre = newBook.getGenre();
        Author author = new Author(newBook.getFio(), newBook.getYear());
        return bookRepository.save(new Book(name, genre, author));
    }

    @GetMapping(value = "{id}")
    public Mono<Book> getBookById(@PathVariable(value = "id") String id) {
        return bookRepository.findById(id);
    }

    @PutMapping
    public Mono<Book> updateBook(@RequestBody UpdatedBook updatedBook) {
        String id = updatedBook.getId();
        String name = updatedBook.getName();
        String genre = updatedBook.getGenre();
        Author author = new Author(updatedBook.getFio(), updatedBook.getYear());
        return bookRepository.save(new Book(id, name, genre, author));
    }

    @DeleteMapping(value = "{id}")
    public Mono<Void> deleteBookById(@PathVariable(value = "id") String id) {
        return bookRepository.deleteById(id);
    }
}
