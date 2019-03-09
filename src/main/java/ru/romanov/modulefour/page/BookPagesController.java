package ru.romanov.modulefour.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.romanov.modulefour.form.NewBook;
import ru.romanov.modulefour.repository.BookRepository;
import ru.romanov.modulefour.repository.GenreRepository;

@Controller
@RequestMapping(value = {"/books"})
public class BookPagesController {

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String showAllBooks(Model model) {
        return "allBooks";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAddBookPage(Model model) {
        return "addBook";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String showEditBookPage(Model model, @RequestParam(value = "id") Long id) {
        model.addAttribute("id", id);
        return "editBook";
    }
}
