package com.cozynest.cozynest.model.dto;


import java.time.LocalDate;



import com.cozynest.cozynest.model.Review;

public class ReviewDTO {

    private String id;
    private int bookId;
    private String bookTitle;
    private String bookCover;
    private String username;
    private String userAvatar;
    private int rating;
    private String reviewText;
    private LocalDate date;

    public static ReviewDTO toDTO(Review review) {
    ReviewDTO dto = new ReviewDTO();
    dto.setId(review.getId().toString());
    dto.setBookId(review.getBook().getId());
    dto.setRating(review.getRating());
    dto.setReviewText(review.getReviewText());
    dto.setDate(review.getDate());
    dto.setBookTitle(review.getBook().getTitle());
    dto.setBookCover(review.getBook().getCoverUrl());
    dto.setUsername(review.getUser().getUsername());
    dto.setUserAvatar(review.getUser().getAvatar());
    return dto;
}



    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public int getBookId() {
        return bookId;
    }


    public void setBookId(int bookId) {
        this.bookId = bookId;
    }



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }
}
