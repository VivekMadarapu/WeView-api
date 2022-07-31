package com.vvk.social.socialapp.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vvk.social.socialapp.models.Post;
import com.vvk.social.socialapp.models.PostRepository;
import com.vvk.social.socialapp.models.Reply;
import com.vvk.social.socialapp.models.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    PostRepository postRepository;
    ReplyRepository replyRepository;

    public PostService(PostRepository postRepository, ReplyRepository replyRepository) {
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
    }

    public List<Post> getPosts() {
        List<Post> posts = postRepository.findAll();
        posts.sort(Comparator.comparing(Post::getDate));
        return posts;
    }

    public List<Reply> getReplies(Long postId) throws NoSuchElementException {
        Post post = postRepository.findById(postId).get(); //throws NoSuchElementException if post is not found
        Gson gson = new Gson();
        JsonObject json = gson.toJsonTree(post.getReplies()).getAsJsonObject();
        Set<Long> replyIds = json.keySet().stream().map(Long::valueOf).collect(Collectors.toSet());

        return replyRepository.findAllById(replyIds);
    }

}
