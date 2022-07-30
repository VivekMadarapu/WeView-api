package com.vvk.social.socialapp.controllers;

import com.vvk.social.socialapp.models.Post;
import com.vvk.social.socialapp.services.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebServiceController {

    PostService postService;

    public WebServiceController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

}
