package com.goit.dev13.entities;

import java.util.Random;

public class Note {
    private Long id;
    private String title;
    private String content;

    public Note() {

    }

    public Note(String title, String content) {
        this.id = new Random().nextLong();
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
