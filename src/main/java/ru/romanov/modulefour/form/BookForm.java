package ru.romanov.modulefour.form;

import java.util.Objects;

public class BookForm {

    private String author;

    private String name;

    private Long genreId;

    public BookForm() {
    }

    public BookForm(String author, String name, Long genreId) {
        this.author = author;
        this.name = name;
        this.genreId = genreId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookForm bookForm = (BookForm) o;
        return Objects.equals(author, bookForm.author) &&
                Objects.equals(name, bookForm.name) &&
                Objects.equals(genreId, bookForm.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, name, genreId);
    }
}
