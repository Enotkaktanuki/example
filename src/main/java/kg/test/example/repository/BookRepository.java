package kg.test.example.repository;

import kg.test.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository <Book,Long> {

    @Modifying
    @Query("UPDATE Book a SET a.author = :authorId WHERE a.id = :bookId")
    void addAuthorToBook(@Param("bookId") Long bookId, @Param("authorId") Long authorId);

}
