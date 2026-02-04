package com.cozynest.cozynest.model.dto;

public class BookDTO {
    private int id;
    private String title;
    private String author;
    private String coverUrl;
    private String isbn;
    private String summary;
    private double score;
    private double count;
    private String genre;

    // Constructor
    public BookDTO(int id ,String title, String author, String coverUrl, String isbn, 
                   String summary, double score, double count, String genre) {
                    this.id = id;
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.isbn = isbn;
        this.summary = summary;
        this.score = score;
        this.count = count;
        this.genre = genre;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCoverUrl() { return coverUrl; }
    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public double getCount() { return count; }
    public void setCount(double count) { this.count = count; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
