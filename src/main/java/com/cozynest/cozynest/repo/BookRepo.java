package com.cozynest.cozynest.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozynest.cozynest.model.Book;




@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {
    Optional<Book> findByTitle(String title);
    Optional<Book>  findById(int id);
        List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCase(
        String title, String author, String genre
    );
}
