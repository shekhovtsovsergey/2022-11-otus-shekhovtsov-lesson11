package ru.otus.lesson13.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "Comments-bookId", attributeNodes = @NamedAttributeNode("book"))
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;*/

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
