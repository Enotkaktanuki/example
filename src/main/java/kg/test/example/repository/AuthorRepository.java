package kg.test.example.repository;

import kg.test.example.model.Author;
import kg.test.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository <Author, Long> {

    @Modifying
    @Query("UPDATE Author a SET a.books = :bookId WHERE a.id = :authorId")
    void addBookToAuthor(@Param("authorId") Long authorId, @Param("bookId") Long bookId);



}
