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
        posts.sort((o1, o2) -> (int)(o2.getDate().getTime()-o1.getDate().getTime()));
        return posts;
    }

    public List<Reply> getRepliesForPost(Long postId) throws NoSuchElementException{
        return replyRepository.findRepliesByPathLikeOrderByPath(postId + "%");
    }

    private List<Long> getReplyIdsForPost(Long postId) {
        return replyRepository.findRepliesByPathLikeOrderByPath(postId + "%").stream().map(Reply::getReplyId).collect(Collectors.toList());
    }

    public Post addPost(Post post) {
        if(post.getDate() == null)
            post.setDate(new java.sql.Date(System.currentTimeMillis()));

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

    public void deletePost(Long postId) throws NoSuchElementException {
        List<Long> replyIds = getReplyIdsForPost(postId);
        postRepository.deleteById(postId);
        replyRepository.deleteAllById(replyIds);
    }


}
