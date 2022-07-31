package com.vvk.social.socialapp.controllers;

import com.vvk.social.socialapp.models.Post;
import com.vvk.social.socialapp.models.Reply;
import com.vvk.social.socialapp.services.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class WebServiceController {

    PostService postService;

    public WebServiceController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/replies")
    public List<Reply> getReplies(@RequestParam(required = true) Long postId) throws NoSuchElementException {
        return postService.getReplies(postId);
    }

}
