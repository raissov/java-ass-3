package kz.raissov.springProject.controller;

import kz.raissov.springProject.exception.ResourceNotFoundException;
import kz.raissov.springProject.model.Book;
import kz.raissov.springProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllUsers(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable(value = "isbn") Long isbn) throws ResourceNotFoundException{
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this isbn : :" + isbn));
        return ResponseEntity.ok().body(book);

    }

    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "isbn") Long isbn, @Valid @RequestBody Book bookDetails) throws ResourceNotFoundException{
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this isbn::" + isbn));

        book.setAuthor(bookDetails.getAuthor());
        book.setTitle(bookDetails.getTitle());
        book.setCount(bookDetails.getCount());
        final Book updateBook = bookRepository.save(book);
        return ResponseEntity.ok(updateBook);
    }

    @DeleteMapping("/books/{isbn}")
    public Map<String, Boolean> deleteBook(@PathVariable(value = "isbn") Long isbn) throws ResourceNotFoundException{
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this isbn :: " + isbn));
        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
