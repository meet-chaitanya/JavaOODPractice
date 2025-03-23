package com.stackoverflow.entity;

public class Comment {
    private final int id;
    private final String content;

    public Comment(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
