package ru.romanov.modulefour.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.romanov.modulefour.domain.Author;
import ru.romanov.modulefour.domain.Book;
import ru.romanov.modulefour.domain.Genre;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setUp() throws Exception {
        mongoTemplate.dropCollection(Book.class);
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(Book.class);
    }

    @Test
    public void findAllGenresTest() {
        Book book1 = new Book("BookName1", "Genre1", new Author("Pushkin", 1800));
        Book book2 = new Book("BookName2", "Genre2", new Author("Pushkin", 1800));
        Book book3 = new Book("BookName3", "Genre1", new Author("Pushkin", 1800));
        mongoTemplate.save(book1);
        mongoTemplate.save(book2);
        mongoTemplate.save(book3);

        Flux<Genre> resource = bookRepository.findAllGenres();

        StepVerifier
                .create(resource)
                .assertNext(genre -> assertEquals(genre.getName(), "Genre1"))
                .assertNext(genre -> assertEquals(genre.getName(), "Genre2"))
                .assertNext(genre -> assertEquals(genre.getName(), "Genre1"))
                .expectComplete()
                .verify();
    }

    @Test
    public void findAllByGenreLikeTest() {
        Book book1 = new Book("BookName1", "Genre1", new Author("Pushkin", 1800));
        Book book2 = new Book("BookName2", "Genre2", new Author("Pushkin", 1800));
        Book book3 = new Book("BookName3", "Genre1", new Author("Pushkin", 1800));
        mongoTemplate.save(book1);
        mongoTemplate.save(book2);
        mongoTemplate.save(book3);

        Flux<Book> resource = bookRepository.findAllByGenreLike("Genre1");

        StepVerifier
                .create(resource)
                .assertNext(book -> assertEquals(book.getName(), "BookName1"))
                .assertNext(book -> assertEquals(book.getName(), "BookName3"))
                .expectComplete()
                .verify();
    }
}
