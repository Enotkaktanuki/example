package kg.test.example.service;


import kg.test.example.model.Author;
import kg.test.example.model.Book;
import kg.test.example.repository.AuthorRepository;
import kg.test.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Autowired
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //вызов книги по айдишнику

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    //внесение книги в базу

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

    //добавление автора в книгу

    public Book addAuthorToBook(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Author author = authorRepository.findById(authorId).orElse(null);

        if (book != null && author != null) {
            book.setAuthor(author);
            return bookRepository.save(book);
        }

        return null;
    }

    public void saveBook(Book book, MultipartFile file1) throws IOException {
        if (file1.getSize() != 0) {
        // Устанавливаем значение title перед сохранением
            book.setTitle("Название вашей книги");
            book.setDescription("Описание вашей книги");

            bookRepository.save(book);
    }
}

}


