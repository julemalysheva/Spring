package org.example.demo4.controller;

import lombok.RequiredArgsConstructor;
import org.example.demo4.exception.BookNotFoundException;
import org.example.demo4.model.Book;
import org.example.demo4.model.Reader;
import org.example.demo4.repo.BookRepo;
import org.example.demo4.repo.ReaderRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookRepo bookRepo;
    private final ReaderRepo readerRepo;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = bookRepo.save(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) {
        Book book = bookRepo.findById(id).orElseThrow(() ->
                new BookNotFoundException("Invalid Book ID: " + id));
        return ResponseEntity.ok(book);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @PutMapping("/{id}/reader/{readerId}")
    public ResponseEntity<Book> assignReaderToBook(@PathVariable Long id, @PathVariable Long readerId) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        Optional<Reader> readerOptional = readerRepo.findById(readerId);

        if (bookOptional.isPresent() && readerOptional.isPresent()) {
            Book book = bookOptional.get();
            Reader reader = readerOptional.get();
            book.setReader(reader);
            bookRepo.save(book);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            bookRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
