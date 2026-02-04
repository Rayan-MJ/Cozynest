package com.cozynest.cozynest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozynest.cozynest.model.Review;


import java.util.List;



@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {

      List<Review> findByBookId(int bookId);
      List<Review> findByUserId(Long userId);
}
