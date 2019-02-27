package ru.romanov.modulefour.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.romanov.modulefour.domain.Book;
import ru.romanov.modulefour.domain.Genre;
import ru.romanov.modulefour.form.BookForm;
import ru.romanov.modulefour.repository.BookRepository;
import ru.romanov.modulefour.repository.GenreRepository;
import ru.romanov.modulefour.rest.BookController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private GenreRepository genreRepository;

    @Test
    public void showAllBooksTest() throws Exception {
        Genre genre = new Genre(1L, "Genre1");
        Book book1 = new Book(1L, "Pushkin", "Name1", genre);
        Book book2 = new Book(2L, "Lermontov", "Name2", genre);
        List<Book> booksList = new ArrayList<>(Arrays.asList(book1, book2));
        when(bookRepository.findAll()).thenReturn(booksList);

        mockMvc.perform(get("/book/getAll"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("allBooks", booksList));
    }

    @Test
    public void showAddBookPageTest() throws Exception {
        mockMvc.perform(get("/book/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("bookForm", new BookForm()));
    }

    @Test
    public void addBookTest() throws Exception {
        Long genreId = 1L;
        BookForm bookForm = new BookForm("Pushkin", "BookName", genreId);
        Genre genre = new Genre(genreId, "Genre");
        when(genreRepository.findById(genreId)).thenReturn(Optional.of(genre));
        mockMvc.perform(post("/book/add").flashAttr("bookForm", bookForm))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/book/getAll"));
        verify(bookRepository).save(new Book("Pushkin", "BookName", genre));
    }

    @Test
    public void showEditBookPageTest() throws Exception {
        Genre genre = new Genre(1L, "Genre1");
        Book book = new Book(1L, "Pushkin", "Name1", genre);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        mockMvc.perform(get("/book/edit").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("bookForm", new BookForm()))
                .andExpect(model().attribute("book", book));
    }

    @Test
    public void updateBookTest() throws Exception {
        Long genreId = 1L;
        BookForm bookForm = new BookForm("Pushkin", "BookName", genreId);
        Genre genre = new Genre(genreId, "Genre");
        when(genreRepository.findById(genreId)).thenReturn(Optional.of(genre));
        mockMvc.perform(put("/book/update").param("id", "1").flashAttr("bookForm", bookForm))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/book/getAll"));
        verify(bookRepository).save(new Book(1L, "Pushkin", "BookName", genre));
    }

    @Test
    public void deleteBookTest() throws Exception {
        mockMvc.perform(delete("/book/delete").param("id", "1"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/book/getAll"));
        verify(bookRepository).deleteById(1L);
    }
}
