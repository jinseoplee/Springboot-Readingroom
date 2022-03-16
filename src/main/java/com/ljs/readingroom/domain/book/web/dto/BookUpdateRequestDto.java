package com.ljs.readingroom.domain.book.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookUpdateRequestDto {
    private String title;
    private String author;
    private String content;

    @Builder
    public BookUpdateRequestDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
