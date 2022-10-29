package com.example.appserver.community;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostSearchCond {
    private String postTitle;
    private String postContent;
    private String postAuthor;

    public PostSearchCond() {
    }

    public PostSearchCond(String postTitle, String postContent, String postAuthor) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postAuthor = postAuthor;
    }
}
