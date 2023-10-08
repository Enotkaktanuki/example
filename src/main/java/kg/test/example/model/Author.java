package kg.test.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_author", nullable = false, length = 100)
    private String name;

    @Column(name = "author_bio", length = 255)
    private String biography;

    @JsonIgnore
    //для отмены цикличности. при гетзапросе для получения инфы обо всех
    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
