package com.greason.need.model;

import java.io.Serializable;

/**
 * need
 * Created by Greason on 15/11/7.
 */
public class NeedModel implements Serializable{
    private int id;
    private String name;
    private String title;
    private String caption;
    private String personImg;
    private String backgroudImg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPersonImg() {
        return personImg;
    }

    public void setPersonImg(String personImg) {
        this.personImg = personImg;
    }

    public String getBackgroudImg() {
        return backgroudImg;
    }

    public void setBackgroudImg(String backgroudImg) {
        this.backgroudImg = backgroudImg;
    }
}
