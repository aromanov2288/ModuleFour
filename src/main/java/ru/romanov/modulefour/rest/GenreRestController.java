package ru.romanov.modulefour.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.modulefour.domain.Genre;
import ru.romanov.modulefour.form.NewGenre;
import ru.romanov.modulefour.form.UpdatedGenre;
import ru.romanov.modulefour.repository.GenreRepository;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/genres"})
public class GenreRestController {

    private GenreRepository genreRepository;

    @Autowired
    public GenreRestController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @PostMapping
    public Genre addGenre(@RequestBody NewGenre newGenre) {
        String name = newGenre.getName();
        return genreRepository.save(new Genre(name));
    }

    @GetMapping(value = "{id}")
    public Genre getGenreById(@PathVariable(value = "id") Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Genre with id %d not found", id)));
    }

    @PutMapping
    public Genre updateGenre(@RequestBody UpdatedGenre updatedGenre) {
        Long id = updatedGenre.getId();
        String name = updatedGenre.getName();
        return genreRepository.save(new Genre(id, name));
    }

    @DeleteMapping(value = "{id}")
    public void deleteGenreById(@PathVariable(value = "id") Long id) {
        genreRepository.deleteById(id);
    }
}
