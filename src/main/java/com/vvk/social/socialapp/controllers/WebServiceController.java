package com.vvk.social.socialapp.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vvk.social.socialapp.models.Post;
import com.vvk.social.socialapp.models.Reply;
import com.vvk.social.socialapp.services.PostService;
import com.vvk.social.socialapp.services.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class WebServiceController {

    PostService postService;
    ReplyService replyService;
    Gson gson = new Gson();

    public WebServiceController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@RequestBody Post post) {
        postService.addPost(post);
    }

    @PatchMapping("/posts")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editPost(@RequestBody String json) {
        JsonObject data = gson.fromJson(json, JsonObject.class);
        if(data.has("title") && data.has("content"))
            postService.updatePost(data.get("id").getAsLong(), data.get("title").getAsString(),data.get("content").getAsString());
        if(data.has("replies"))
            /* format
                {"id": id, "path": path}
             */
            postService.updatePostReplies(data.get("id").getAsLong(), data.get("replies").getAsString());
    }

    @DeleteMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@RequestBody String id) {
        postService.deletePost(Long.parseLong(id));
    }

    @GetMapping("/replies")
    public List<Reply> getReplies(@RequestParam Long postId) throws NoSuchElementException {
        return postService.getRepliesForPost(postId);
    }

    @PostMapping("/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReply(@RequestBody Reply reply) {
        replyService.addReply(reply);
    }

    @PatchMapping("/replies")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editReply(@RequestBody String json) {
        JsonObject data = gson.fromJson(json, JsonObject.class);
        replyService.updateReply(data.get("id").getAsLong(), data.get("content").getAsString());
    }

    @DeleteMapping("/replies")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReply(@RequestBody String id) {
        replyService.deleteReply(Long.parseLong(id));
    }

}
