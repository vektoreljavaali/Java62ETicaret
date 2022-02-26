package com.vektorel.dao.entity;

public class Note {
       String id;
       String username;
       String title;
        String content;
        long publishAt;
        int status;
        String priority;

    public Note(String id, String username,
                String title, String content,
                long publishAt, int status,
                String priority) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.publishAt = publishAt;
        this.status = status;
        this.priority = priority;
    }

    public Note() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public long getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(long publishAt) {
        this.publishAt = publishAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
