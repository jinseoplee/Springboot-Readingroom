package com.ljs.readingroom.domain.book.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @AfterEach
    public void cleanup() {
        bookRepository.deleteAll();
    }

    @Test
    public void saveAndFindBookTest() {
        // given
        String title = "title";
        String author = "author";
        String content = "content";

        bookRepository.save(Book.builder().title(title).author(author).content(content).build());

        // when
        List<Book> bookList = bookRepository.findAll();

        // then
        Book book = bookList.get(0);
        assertThat(book.getTitle()).isEqualTo(title);
        assertThat(book.getAuthor()).isEqualTo(author);
        assertThat(book.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntityTest() {
        // given
        LocalDateTime now = LocalDateTime.now();

        bookRepository.save(Book.builder().title("title").author("author").content("content").build());

        // when
        List<Book> bookList = bookRepository.findAll();

        // then
        Book book = bookList.get(0);

        assertThat(book.getCreatedDate()).isAfter(now);
        assertThat(book.getModifiedDate()).isAfter(now);
    }

}