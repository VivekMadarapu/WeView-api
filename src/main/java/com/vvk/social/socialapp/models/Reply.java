package com.vvk.social.socialapp.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "REPLY")
public class Reply {

    @Id
    @Column(name = "REPLY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long replyId;
    @Column(name="AUTHOR")
    private String author;
    @Column(name="CONTENT")
    private String content;
    @Column(name="DATE")
    private Date date;
    @Column(name="LIKES")
    private int likes;
    @Column(name="DELETED")
    private boolean deleted;

    public long getReplyId() {
        return replyId;
    }

    public void setReplyId(long replyId) {
        this.replyId = replyId;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likes=" + likes +
                '}';
    }
}
