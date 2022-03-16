package com.ljs.readingroom.domain.book.service;

import com.ljs.readingroom.domain.book.repository.Book;
import com.ljs.readingroom.domain.book.repository.BookRepository;
import com.ljs.readingroom.domain.book.web.dto.BookResponseDto;
import com.ljs.readingroom.domain.book.web.dto.BookSaveRequestDto;
import com.ljs.readingroom.domain.book.web.dto.BookUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookResponseDto findById(Long id) {
        Book entity = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));

        return new BookResponseDto(entity);
    }

    @Transactional
    public Long save(BookSaveRequestDto requestDto) {
        return bookRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BookUpdateRequestDto requestDto) {
        Book entity = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));

        entity.update(requestDto.getTitle(), requestDto.getAuthor(), requestDto.getContent());

        return id;
    }

}
