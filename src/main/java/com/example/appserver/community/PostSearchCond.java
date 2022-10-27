package com.example.appserver.community;

import lombok.Data;

@Data
public class PostSearchCond {
    private String postTitle;
    private String postAuthor;

    public PostSearchCond() {
    }
    public PostSearchCond(String postTitle, String postAuthor) {
        this.postTitle = postTitle;
        this.postAuthor = postAuthor;
    }
}
