package com.ljs.readingroom.domain.book.web.dto;

import com.ljs.readingroom.domain.book.repository.Book;
import lombok.Getter;

@Getter
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;

    public BookResponseDto(Book entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
    }
}
