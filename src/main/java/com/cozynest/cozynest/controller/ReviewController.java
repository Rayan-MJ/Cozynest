package com.cozynest.cozynest.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozynest.cozynest.model.Book;
import com.cozynest.cozynest.model.Review;
import com.cozynest.cozynest.model.User;
import com.cozynest.cozynest.model.dto.ReviewDTO;
import com.cozynest.cozynest.repo.BookRepo;
import com.cozynest.cozynest.repo.ReviewRepo;
import com.cozynest.cozynest.repo.UserRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/review")
@RestController
public class ReviewController {
		final ReviewRepo repo;
                final UserRepo userRepo;	
                final BookRepo bookRepo;

    ReviewController(ReviewRepo repo, UserRepo userRepo, BookRepo bookRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

        @GetMapping("/getallreviews")
public List<ReviewDTO> getReviews() {
    return repo.findAll()
            .stream()
            .map(ReviewDTO::toDTO)
            .toList();
}
        @GetMapping("/bookreview")
public List<ReviewDTO> getReviewsForBook(@RequestParam int id) {
    return repo.findByBookId(id)
            .stream()
            .map(ReviewDTO::toDTO)
            .toList();
}
        @GetMapping("/userreview")
public List<ReviewDTO> getReviewsForUser(@RequestParam String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        Long id = user.getId();
    return repo.findByUserId(id)
            .stream()
            .map(ReviewDTO::toDTO)
            .toList();
}
@PostMapping("/writereview")
public ResponseEntity<Map<String, Object>> writeReview(
        @RequestBody Map<String, Object> body) {

    final String USERNAME = "username";
    final String BOOK_ID = "bookId";
    final String RATING = "rating";
    final String REVIEW_TEXT = "reviewText";
    final String DATE = "date";

    Map<String, Object> response = Map.of(
        USERNAME, body.get(USERNAME),
        BOOK_ID, body.get(BOOK_ID),
        RATING, body.get(RATING),
        REVIEW_TEXT, body.get(REVIEW_TEXT),
        DATE, body.get(DATE)
    );

    User user = userRepo.findByUsername((String) body.get(USERNAME))
        .orElseThrow(() -> new RuntimeException("User not found"));

    Book book = bookRepo.findById(Integer.parseInt((String) body.get(BOOK_ID)))
        .orElseThrow(() -> new RuntimeException("Book not found"));

    Review review = new Review();
    review.setUser(user);
    review.setBook(book);
    review.setReviewText((String) body.get(REVIEW_TEXT));
    review.setRating((int) body.get(RATING));
    review.setDate(LocalDate.parse((String) body.get(DATE)));

    repo.save(review);
    

    return ResponseEntity.ok(response);
}

}
