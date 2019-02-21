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
import ru.romanov.modulefour.form.GenreForm;
import ru.romanov.modulefour.repository.BookRepository;
import ru.romanov.modulefour.repository.GenreRepository;

import java.util.List;

@Controller
@RequestMapping(value = {"/genre"})
public class GenreController {

    private GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String showAllGenres(Model model) {
        List<Genre> allGenres = genreRepository.findAll();
        model.addAttribute("allGenres", allGenres);
        return "allGenres";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAddGenrePage(Model model) {
        GenreForm genreForm = new GenreForm();
        model.addAttribute("genreForm", genreForm);
        return "addGenre";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addPerson(Model model, @ModelAttribute("genreForm") GenreForm genreForm) {
        String name = genreForm.getName();
        genreRepository.save(new Genre(name));
        return "redirect:/genre/getAll";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String showEditGenrePage(Model model, @RequestParam(value = "id") Long id) {
        GenreForm genreForm = new GenreForm();
        model.addAttribute("genreForm", genreForm);
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Genre with id %d not found", id)));
        model.addAttribute("genre", genre);
        return "editGenre";
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.PUT)
    public String updateGenre(Model model, @RequestParam(value = "id") Long id, @ModelAttribute("genreForm") GenreForm genreForm) {
        String name = genreForm.getName();
        genreRepository.save(new Genre(id, name));
        return "redirect:/genre/getAll";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.DELETE)
    public String deleteBook(Model model, @RequestParam(value = "id") Long id) {
        genreRepository.deleteById(id);
        return "redirect:/genre/getAll";
    }
}
