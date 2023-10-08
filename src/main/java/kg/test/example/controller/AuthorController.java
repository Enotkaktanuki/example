package kg.test.example.controller;

import kg.test.example.model.Author;
import kg.test.example.model.Book;
import kg.test.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getBookById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping("/create")
    public Author createBook(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @PutMapping("/update/{id}")
    public Author updateBook(@PathVariable Long id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
    @PutMapping("/{bookId}/addAuthor/{authorId}")
    public Author addBookToAuthor(@PathVariable Long authorId, @PathVariable Long bookId) {
        return authorService.addBookToAuthor(bookId, authorId);
    }

}
