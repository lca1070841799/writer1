package com.writer1.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Messages {
    private int id;
    private int sid;
    private String message;
    private String comment;
    private int isRead;

    public Messages(int sid, String message, String comment) {
        this.sid = sid;
        this.message = message;
        this.comment = comment;
    }
}
