package kg.test.example.controller;

import kg.test.example.model.Author;
import kg.test.example.model.Book;
import kg.test.example.model.dto.AddAuthorToBookDTO;
import kg.test.example.model.dto.BookDTO;
import kg.test.example.service.AuthorService;
import kg.test.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        // Преобразуйте сущность Book в DTO
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        return bookDTO;
    }

    @PostMapping("/create")
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        //  DTO в Сущность
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        Book savedBook = bookService.createBook(book);

        // Сущность Book обратно в DTO
        BookDTO savedBookDTO = new BookDTO();
        savedBookDTO.setId(savedBook.getId());
        savedBookDTO.setTitle(savedBook.getTitle());
        savedBookDTO.setDescription(savedBook.getDescription());
        return savedBookDTO;
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/{bookId}/addAuthor/{authorId}")
    public ResponseEntity<Void> addAuthorToBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        bookService.addAuthorToBook(bookId, authorId);
        return ResponseEntity.ok().build();
    }


}
