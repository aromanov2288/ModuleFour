package ru.romanov.modulefour.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.romanov.modulefour.domain.Book;
import ru.romanov.modulefour.domain.Genre;
import ru.romanov.modulefour.form.BookForm;
import ru.romanov.modulefour.repository.BookRepository;
import ru.romanov.modulefour.repository.GenreRepository;

import java.util.List;

@Controller
@RequestMapping(value = {"/book"})
public class BookController {

    private BookRepository bookRepository;

    private GenreRepository genreRepository;

    @Autowired
    public BookController(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String showAllBooks(Model model) {
        List<Book> allBooks = bookRepository.findAll();
        model.addAttribute("allBooks", allBooks);
        return "allBooks";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAddBookPage(Model model) {
        BookForm bookForm = new BookForm();
        model.addAttribute("bookForm", bookForm);
        return "addBook";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addBook(Model model, @ModelAttribute("bookForm")BookForm bookForm) {
        String author = bookForm.getAuthor();
        String name = bookForm.getName();
        Long genreId = bookForm.getGenreId();
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new RuntimeException(String.format("Genre with id %d not found", genreId)));
        bookRepository.save(new Book(author, name, genre));
        return "redirect:/book/getAll";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String showEditBookPage(Model model, @RequestParam(value = "id") Long id) {
        BookForm bookForm = new BookForm();
        model.addAttribute("bookForm", bookForm);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Book with id %d not found", id)));
        model.addAttribute("book", book);
        return "editBook";
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.PUT)
    public String updateBook(Model model, @RequestParam(value = "id") Long id, @ModelAttribute("bookForm")BookForm bookForm) {
        String author = bookForm.getAuthor();
        String name = bookForm.getName();
        Long genreId = bookForm.getGenreId();
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new RuntimeException(String.format("Genre with id %d not found", genreId)));
        bookRepository.save(new Book(id, author, name, genre));
        return "redirect:/book/getAll";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.DELETE)
    public String deleteBook(Model model, @RequestParam(value = "id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/book/getAll";
    }
}
