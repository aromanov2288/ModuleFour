package ru.romanov.modulefour.form;

public class UpdatedBook {

    private String id;

    private String name;

    private String genre;

    private String fio;

    private Integer year;

    public UpdatedBook() {
    }

    public UpdatedBook(String id, String name, String genre, String fio, Integer year) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.fio = fio;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
