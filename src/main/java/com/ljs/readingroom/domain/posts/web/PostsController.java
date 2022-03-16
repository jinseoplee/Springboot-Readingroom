package com.ljs.readingroom.domain.posts.web;

import com.ljs.readingroom.domain.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("postsList", postsService.findAllDesc());
        return "posts/posts";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts/posts-save";
    }

}
