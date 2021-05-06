package com.disertatie.rent.car.entities;

import com.disertatie.rent.car.model.enumType.CommentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "text")
    private String text;

    @Column(name = "author")
    private String author;

    @Column(name = "author_email")
    private String authorEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CommentType status;

    @Column(name = "rating")
    private Long rating;

    public Comment() {
    }
}
