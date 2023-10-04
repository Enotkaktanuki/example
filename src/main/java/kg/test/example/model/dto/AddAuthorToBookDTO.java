package kg.test.example.model.dto;

import lombok.Data;

@Data
public class AddAuthorToBookDTO {
    private Long bookId;
    private Long authorId;
}

