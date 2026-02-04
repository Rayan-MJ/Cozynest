
package com.cozynest.cozynest.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cozynest.cozynest.model.Book;
import com.cozynest.cozynest.model.User;
import com.cozynest.cozynest.model.dto.BookDTO;
import com.cozynest.cozynest.model.dto.LogEntryDTO;
import com.cozynest.cozynest.producer.LogProducer;
import com.cozynest.cozynest.repo.BookRepo;
import com.cozynest.cozynest.repo.UserRepo;
import com.cozynest.cozynest.repo.WantToreadRepo;


@RestController
@RequestMapping("/books")
public class BookController {
		final BookRepo repo;	
        final WantToreadRepo wantToreadRepo;
        final UserRepo userRepo;
        private final LogProducer bookProducer;
    BookController(BookRepo repo, WantToreadRepo wantToreadRepo, UserRepo userRepo, LogProducer bookProducer) {
        this.repo = repo;
        this.wantToreadRepo = wantToreadRepo;
        this.userRepo = userRepo;
        this.bookProducer = bookProducer;
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("q") String query) {
        return repo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCase(
            query, query, query
        );
    }

@GetMapping("/search/{id}")
public ResponseEntity<List<Book>> searchBooks(@PathVariable int id) {
    return repo.findById(id)
               .map(book -> ResponseEntity.ok(Collections.singletonList(book))) 
               .orElse(ResponseEntity.notFound().build()); 
}
        @GetMapping("/homepage")
    public List<Book> homepage() {
            PageRequest first25 = PageRequest.of(0, 25); // page 0, size 25
    return repo.findAll(first25).getContent();

    }
        @GetMapping("/getallbooks")
    public List<Book>  getAllBooks() {
        return repo.findAll();
    }
    @GetMapping("/wanttoread")
    public ResponseEntity<List<BookDTO>> getMethodName(Authentication auth) {
        User user = userRepo.findByUsername(auth.getName()).orElseThrow();
       List<BookDTO> books = wantToreadRepo.findByUser(user).stream()
    .map(wtr -> {
        Book b = wtr.getBook();
        return new BookDTO(
            b.getId(),
            b.getTitle(),
            b.getAuthor(),
            b.getCoverUrl(),
            b.getIsbn(),
            b.getSummary(),
            b.getScore(),
            b.getCount(),
            b.getGenre()
        );
    })
    .toList();

    return ResponseEntity.ok(books);
        

        
    }
    
    
}
