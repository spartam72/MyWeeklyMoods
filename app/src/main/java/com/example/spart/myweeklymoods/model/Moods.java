package com.example.spart.myweeklymoods.model;

import java.util.Date;
//creating the moods
public class Moods {

    private int image;
    private int Background;
    private int number;
    private String comment;
    private Date date;
    private int sound;

    //Moods constructor
    public Moods(int image, int background, int number,String comment, Date date,int sound){
        this.image = image;
        this.Background = background;
        this.number = number;
        this.comment = comment;
        this.date = date;
        this.sound = sound;

    }
    //ghetters and setters
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() { return date; }

    public int getImage() { return image; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public int getBackground() { return Background; }

    public void setBackground(int background) { Background = background; }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public int getSound() { return sound; }

}









