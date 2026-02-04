package com.cozynest.cozynest.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "books")
@Entity
 public class Book {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String author;
    private String coverUrl;
    private String isbn;
    private String summary;
    private double score;
    private double count;
    private String genre;
    

    public Book(int id, String title, String author, String coverUrl, String isbn, String summary, double score,
            double count, String genre) {
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

    
    public Book() {
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getId() {
        return id;
    }
    public String getCoverUrl() {
        return coverUrl;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getSummary() {
        return summary;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override

public String toString() {
    return "Book{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", coverUrl='" + coverUrl + '\'' +
            ", isbn='" + isbn + '\'' +
            ", summary='" + (summary) + '\'' +
            ", rating=" + score + "count" + count +
            "Genre" + genre +
            '}';
}
    public double getScore() {
        return score;
    }
    public double getCount() {
        return count;
    }

    public String getGenre() {
        return genre;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setCount(double count) {
        this.count = count;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    
}
