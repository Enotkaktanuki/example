package kg.test.example.service;

import kg.test.example.model.Author;
import kg.test.example.model.Book;
import kg.test.example.model.dto.BookDTO;
import kg.test.example.repository.AuthorRepository;
import kg.test.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    //получение всех книг в библиотеке
    @Autowired
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    //получение той самой книги
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    //добавление книги
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    //обновление данных книги
    public Book updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            return bookRepository.save(book);
        }
        return null;
    }
    //удаление книги
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    //конвертация в ДТО
    private BookDTO convertBookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        return bookDTO;
    }
    public void addAuthorToBook(Long bookId, Long authorId) {
        bookRepository.addAuthorToBook(bookId, authorId);
    }



}


