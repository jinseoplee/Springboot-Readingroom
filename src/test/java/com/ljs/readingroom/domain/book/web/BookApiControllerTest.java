package com.ljs.readingroom.domain.book.web;

import com.ljs.readingroom.domain.book.repository.Book;
import com.ljs.readingroom.domain.book.repository.BookRepository;
import com.ljs.readingroom.domain.book.web.dto.BookSaveRequestDto;
import com.ljs.readingroom.domain.book.web.dto.BookUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    public void cleanup() {
        bookRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        // given
        String title = "title";
        String author = "author";
        String content = "content";

        BookSaveRequestDto requestDto = BookSaveRequestDto.builder().title(title).author(author).content(content).build();

        String url = "http://localhost:" + port + "/api/v1/book";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList.get(0).getTitle()).isEqualTo(title);
        assertThat(bookList.get(0).getAuthor()).isEqualTo(author);
        assertThat(bookList.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void updateTest() {
        // given
        Book savedBook = bookRepository.save(Book.builder().title("title").author("author").content("content").build());

        Long updateId = savedBook.getId();
        String expectedTitle = "title2";
        String expectedAuthor = "author2";
        String expectedContent = "content2";

        BookUpdateRequestDto requestDto = BookUpdateRequestDto.builder().title(expectedTitle).author(expectedAuthor).content(expectedContent).build();

        String url = "http://localhost:" + port + "/api/v1/book/" + updateId;

        HttpEntity<BookUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(bookList.get(0).getAuthor()).isEqualTo(expectedAuthor);
        assertThat(bookList.get(0).getContent()).isEqualTo(expectedContent);
    }

}