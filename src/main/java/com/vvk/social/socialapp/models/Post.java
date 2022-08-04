package com.vvk.social.socialapp.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="POST")
public class Post {
    @Id
    @Column(name="POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    @Column(name="TITLE")
    private String title;
    @Column(name="AUTHOR")
    private String author;
    @Column(name="CONTENT")
    private String content;
    @Column(name="DATE")
    private Date date;
    @Column(name="LIKES")
    private int likes;

    /* Reply tree format:
        {
reply id -> "1":"1", <- placement in reply tree
in db       "2":"2", <- second reply
            "3":"2.1", <- first subreply of reply 2
            "4":"2.2"  <- second subreply of reply 2
            "5":"2.1.1", <- first subreply of first subreply of reply 2
            etc...
        }
     */
    @Column(name="REPLIES")
    private String replies;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likes=" + likes +
                ", replies='" + replies + '\'' +
                '}';
    }
}
