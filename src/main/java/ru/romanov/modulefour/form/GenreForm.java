package ru.romanov.modulefour.form;

import java.util.Objects;

public class GenreForm {

    private String name;

    public GenreForm() {
    }

    public GenreForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreForm genreForm = (GenreForm) o;
        return Objects.equals(name, genreForm.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
