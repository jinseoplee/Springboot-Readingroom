package com.ljs.readingroom.domain.book.web;

import com.ljs.readingroom.domain.book.service.BookService;
import com.ljs.readingroom.domain.book.web.dto.BookResponseDto;
import com.ljs.readingroom.domain.book.web.dto.BookSaveRequestDto;
import com.ljs.readingroom.domain.book.web.dto.BookUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BookService bookService;

    @GetMapping("/api/v1/book/{id}")
    public BookResponseDto findById(@PathVariable Long id){
        return bookService.findById(id);
    }

    @PostMapping("/api/v1/book")
    public Long save(@RequestBody BookSaveRequestDto requestDto){
        return bookService.save(requestDto);
    }

    @PutMapping("/api/v1/book/{id}")
    public Long update(@PathVariable Long id, @RequestBody BookUpdateRequestDto requestDto){
        return bookService.update(id, requestDto);
    }

}
