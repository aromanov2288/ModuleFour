package ru.romanov.modulefour.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.romanov.modulefour.domain.Author;
import ru.romanov.modulefour.domain.Book;
import ru.romanov.modulefour.form.NewBook;
import ru.romanov.modulefour.form.UpdatedBook;
import ru.romanov.modulefour.repository.BookRepository;
import ru.romanov.modulefour.rest.BookRestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebFluxTest(BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private BookRepository bookRepository;

    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    public void getAllTest() throws Exception {
        Book book1 = new Book("aaa", "Name1","Genre1", new Author("Pushkin", 1800));
        Book book2 = new Book("bbb", "Name2","Genre2", new Author("Lermontov", 1800));
        List<Book> booksList = new ArrayList<>(Arrays.asList(book1, book2));
        when(bookRepository.findAll()).thenReturn(Flux.fromIterable(booksList));
        String jsonResponse = objectWriter.writeValueAsString(booksList);

        webClient.get().uri("/api/books")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody().json(jsonResponse);
    }

    @Test
    public void addBookTest() throws Exception {
        NewBook newBook = new NewBook("BookName", "Genre", "Pushkin", 1800);
        Book bookForSave = new Book("BookName", "Genre", new Author("Pushkin", 1800));
        Book savedBook = new Book("aaa", "BookName", "Genre", new Author("Pushkin", 1800));
        when(bookRepository.save(bookForSave))
                .thenReturn(Mono.just(savedBook));
        String jsonResponse = objectWriter.writeValueAsString(savedBook);

        webClient.post().uri("/api/books").body(BodyInserters.fromObject(newBook))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody().json(jsonResponse);
    }

    @Test
    public void getByIdTest() throws Exception {
        Book book = new Book("aaa", "BookName", "Genre", new Author("Pushkin", 1800));
        when(bookRepository.findById("aaa")).thenReturn(Mono.just(book));
        String jsonResponse = objectWriter.writeValueAsString(book);

        webClient.get().uri("/api/books/aaa")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody().json(jsonResponse);
    }

    @Test
    public void updateBookTest() throws Exception {
        UpdatedBook updatedBook = new UpdatedBook("aaa", "BookName", "Genre", "Pushkin", 1800);
        Book book = new Book("aaa", "BookName", "Genre", new Author("Pushkin", 1800));
        when(bookRepository.save(book))
                .thenReturn(Mono.just(book));
        String jsonResponse = objectWriter.writeValueAsString(book);

        webClient.put().uri("/api/books").body(BodyInserters.fromObject(updatedBook))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody().json(jsonResponse);
    }

    @Test
    public void deleteBookById() throws Exception {
        webClient.delete().uri("/api/books/aaa").exchange().expectStatus().isOk();
        verify(bookRepository).deleteById("aaa");
    }
}
