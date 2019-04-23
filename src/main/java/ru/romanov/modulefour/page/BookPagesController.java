package ru.romanov.modulefour.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookPagesController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/books/getAll", method = RequestMethod.GET)
    public String showAllBooks(Model model) {
        return "allBooks";
    }

    @RequestMapping(value = {"/books/add"}, method = RequestMethod.GET)
    public String showAddBookPage(Model model) {
        return "addBook";
    }

    @RequestMapping(value = {"/books/edit"}, method = RequestMethod.GET)
    public String showEditBookPage(Model model, @RequestParam(value = "id") String id) {
        model.addAttribute("id", id);
        return "editBook";
    }
}
