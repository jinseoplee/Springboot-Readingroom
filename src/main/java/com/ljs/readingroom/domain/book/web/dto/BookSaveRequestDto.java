package com.ljs.readingroom.domain.book.web.dto;

import com.ljs.readingroom.domain.book.repository.Book;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookSaveRequestDto {
    private String title;
    private String author;
    private String content;

    @Builder
    public BookSaveRequestDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Book toEntity(){
        return Book.builder().title(title).author(author).content(content).build();
    }
}