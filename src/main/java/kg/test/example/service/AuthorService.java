package kg.test.example.service;

import kg.test.example.model.Author;
import kg.test.example.model.Book;
import kg.test.example.repository.AuthorRepository;
import kg.test.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    @Autowired
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    //получение автора по айди

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    //внесение автора в БД

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    //обновление информации

    public Author updateAuthor(Long id, Author author) {
        if (authorRepository.existsById(id)) {
            author.setId(id);
            return authorRepository.save(author);
        }
        return null;
    }

    //удаление автора из БД

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    //добавление книги через автора (но добавление автора через книгу удобнее получается)

    public Author addBookToAuthor(Long authorId, Long bookId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (author != null && book != null) {

            author.getBooks().add(book);
            book.setAuthor(author);

            authorRepository.save(author);

            return author;
        }

        return null;
    }
}

