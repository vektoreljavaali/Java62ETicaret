package com.vektorel.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
import java.util.UUID;

@IgnoreExtraProperties
public class Message {

    String id;
    String user;
    String message;
    String time;

    public Message() {
    }

    public Message(String user, String message) {
        Date date = new Date();
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.message = message;
        this.time = date.getHours()+":"+date.getMinutes();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
