package ru.otus.lesson11.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "comments")
@NamedEntityGraph(name = "Comments-bookId", attributeNodes = @NamedAttributeNode("book"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "book_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_books_comments"),
            nullable = false)
    private Book book;

    @Column(name = "author_name", nullable = false)
    private String authorName;

    @Column(name = "comment", nullable = false)
    private String comment;


    public String toString() {
        return "Comment{"
                + "id='" + id + '\''
                + ", authorName=" + authorName
                + ", comment=" + comment
                + '}';
    }


}
