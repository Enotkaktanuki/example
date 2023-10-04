package kg.test.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title",nullable = false,length = 255)
    private String title;

    @Column(name = "book_description",columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;
}
