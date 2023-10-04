package kg.test.example.controller;

import kg.test.example.model.Author;
import kg.test.example.model.dto.AuthorDTO;
import kg.test.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public AuthorDTO getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        // Преобразуйте сущность Author в DTO
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setBiography(author.getBiography());
        return authorDTO;
    }

    @PostMapping("/create")
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
        // DTO в сущность Author
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());
        Author savedAuthor = authorService.createAuthor(author);

        // сущность Author обратно в DTO
        AuthorDTO savedAuthorDTO = new AuthorDTO();
        savedAuthorDTO.setId(savedAuthor.getId());
        savedAuthorDTO.setName(savedAuthor.getName());
        savedAuthorDTO.setBiography(savedAuthor.getBiography());
        return savedAuthorDTO;
    }

    @PutMapping("/update/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @PostMapping("/{authorId}/addBook/{bookId}")
    public ResponseEntity<Void> addBookToAuthor(@PathVariable Long authorId, @PathVariable Long bookId) {
        authorService.addBookToAuthor(authorId, bookId);
        return ResponseEntity.ok().build();
    }
}
