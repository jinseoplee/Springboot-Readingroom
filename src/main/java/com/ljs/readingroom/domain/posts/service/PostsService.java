package com.ljs.readingroom.domain.posts.service;

import com.ljs.readingroom.domain.posts.repository.Posts;
import com.ljs.readingroom.domain.posts.repository.PostsRepository;
import com.ljs.readingroom.domain.posts.web.dto.PostsResponseDto;
import com.ljs.readingroom.domain.posts.web.dto.PostsSaveRequestDto;
import com.ljs.readingroom.domain.posts.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id = " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id = " + id));

        entity.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

}