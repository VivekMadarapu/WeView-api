package com.vvk.social.socialapp.services;

import com.vvk.social.socialapp.models.Post;
import com.vvk.social.socialapp.models.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PostService {

    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        List<Post> posts = postRepository.findAll();
        posts.sort(Comparator.comparing(Post::getDate));
        return posts;
    }
}
