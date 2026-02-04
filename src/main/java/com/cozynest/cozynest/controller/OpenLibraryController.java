package com.cozynest.cozynest.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cozynest.cozynest.model.Book;
import com.cozynest.cozynest.service.OpenLibrary;
import java.util.List;

@RestController
@RequestMapping("/openlibrary")
public class OpenLibraryController {
        @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String q) {
        return OpenLibrary.searchBooks(q);
    }
}
