package ru.romanov.modulefour.form;

public class NewBook {

    private String name;

    private String genre;

    private String fio;

    private Integer year;

    public NewBook() {
    }

    public NewBook(String name, String genre, String fio, Integer year) {
        this.name = name;
        this.genre = genre;
        this.fio = fio;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
