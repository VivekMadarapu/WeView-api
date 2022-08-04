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
    Gson gson = new Gson();

    public PostService(PostRepository postRepository, ReplyRepository replyRepository) {
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
    }

    public List<Post> getPosts() {
        List<Post> posts = postRepository.findAll();
        posts.sort(Comparator.comparing(Post::getDate));
        return posts;
    }

    public Set<Long> getReplyIdsForPost(Long postId) throws NoSuchElementException{
        Post post = postRepository.findById(postId).get(); //throws NoSuchElementException if post is not found
        JsonObject json = gson.toJsonTree(post.getReplies()).getAsJsonObject();
        return json.keySet().stream().map(Long::valueOf).collect(Collectors.toSet());
    }

    public List<Reply> getRepliesForPost(Long postId) throws NoSuchElementException {
        return replyRepository.findAllById(getReplyIdsForPost(postId));
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, String title, String content) {
        Post post = postRepository.getReferenceById(id);
        if (!title.isEmpty())
            post.setTitle(title);
        if (!content.isEmpty())
            post.setContent(content);

        return postRepository.save(post);
    }

    public Post updatePostReplies(Long postId, String newReplies) {
        Post post = postRepository.getReferenceById(postId);
        JsonObject json = gson.fromJson(post.getReplies(), JsonObject.class);
        JsonObject newReplyData = gson.fromJson(newReplies, JsonObject.class);
        json.addProperty(newReplyData.get("id").getAsString(), newReplyData.get("path").getAsString());

        post.setReplies(json.getAsString());
        return postRepository.save(post);
    }

    public void deletePost(Long postId) throws NoSuchElementException {
        Set<Long> replyIds = getReplyIdsForPost(postId);
        postRepository.deleteById(postId);
        replyRepository.deleteAllById(replyIds);
    }

}
