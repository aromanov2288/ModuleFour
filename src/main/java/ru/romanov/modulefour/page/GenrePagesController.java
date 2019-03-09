package ru.romanov.modulefour.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.romanov.modulefour.domain.Genre;
import ru.romanov.modulefour.form.NewGenre;
import ru.romanov.modulefour.repository.GenreRepository;

@Controller
@RequestMapping(value = {"/genres"})
public class GenrePagesController {

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String showAllGenres(Model model) {
        return "allGenres";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAddGenrePage(Model model) {
        return "addGenre";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String showEditGenrePage(Model model, @RequestParam(value = "id") Long id) {
        model.addAttribute("id", id);
        return "editGenre";
    }
}
