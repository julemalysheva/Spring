package org.example.demo4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

    @ManyToOne(optional = true)
    @JoinColumn(name = "reader_id", nullable = true)
//    @JsonIgnore
    private Reader reader;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
