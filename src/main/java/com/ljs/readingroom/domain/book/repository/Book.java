package com.ljs.readingroom.domain.book.repository;

import com.ljs.readingroom.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @Builder
    public Book(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public void update(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}