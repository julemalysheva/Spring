package com.example.books_store;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
//@Api(tags = "My API")
public class BookController {
    private final BookService bookService;

    @GetMapping
//    @ApiOperation("Get list all books")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    /**
     * ResponseEntity обертка над нашим классом которая позволяет
     * задать параметры для ответа
     * ResponseEntity - это класс в Spring Framework,
     * который представляет собой ответ, отправленный клиенту
     * при выполнении HTTP-запроса. Он содержит не только тело
     * ответа, но и метаданные, такие как HTTP-статус, заголовки и т.д.
     * Например, если мы хотим вернуть статус 404 Not Found, можно использовать метод notFound():
     * @param id
     * @return
     */
    @GetMapping("/{id}")
//    @ApiOperation("Get information about Book with ID")
    public ResponseEntity<Book> findById(@PathVariable Long id) { //@ApiParam("ID of the Book")
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Автоматически при помощи Jackson произойдет конвертация входящего Json
     * в сущность класса Book
     * @param book
     * @return
     */
    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@RequestBody Book book, @PathVariable Long id) {
        book.setId(id);
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
