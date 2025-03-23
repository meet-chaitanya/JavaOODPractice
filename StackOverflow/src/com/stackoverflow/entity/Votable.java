package com.stackoverflow.entity;

public interface Votable {
    void upVote();
    void downVote();
    int getVoteCount();
}
