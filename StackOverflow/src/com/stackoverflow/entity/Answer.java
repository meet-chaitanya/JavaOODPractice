package com.stackoverflow.entity;

import java.util.ArrayList;
import java.util.List;

public class Answer implements Votable {
    private final String id;
    private final String questionId;
    private final String content;
    private final String authorId;
    private List<Comment> comments;
    private boolean isAccepted;
    private int upVotes;
    private int downVotes;

    public Answer(String id, String questionId, String content, String authorId) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.authorId = authorId;
        this.comments = new ArrayList<>();
        this.isAccepted = false;
        this.upVotes = 0;
        this.downVotes = 0;
    }

    public String getId() {
        return id;
    }

    public String getQuestionId() {
        return questionId;
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

    public boolean isAccepted() {
        return isAccepted;
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
