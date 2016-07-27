package com.augmentis.ayp.keepwalking;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Wilailux on 7/27/2016.
 */
public class KeepWalking {
    private UUID id;
    private String title;
    private Date date;

    public KeepWalking(){
        id = UUID.randomUUID();
        date = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UUID=").append(id);
        builder.append(",Title=").append(title);
        builder.append(",KeepWalking Date=").append(date);
        return builder.toString();
    }

}
