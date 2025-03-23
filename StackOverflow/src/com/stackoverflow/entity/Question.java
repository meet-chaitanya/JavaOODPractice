package com.stackoverflow.entity;

import java.util.ArrayList;
import java.util.List;

public class Question implements Votable{
    private final String id;
    private final String title;
    private final String content;
    private final String authorId;
    private List<Comment> comments;
    private List<Tag> tags;
    private int upVotes;
    private int downVotes;

    public Question(String id, String title, String content, String authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        comments = new ArrayList<>();
        tags = new ArrayList<>();
        upVotes = 0;
        downVotes = 0;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorId() {
        return authorId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    @Override
    public void upVote() {
        upVotes++;
    }

    @Override
    public void downVote() {
        downVotes++;
    }

    @Override
    public int getVoteCount() {
        return upVotes - downVotes;
    }
}
