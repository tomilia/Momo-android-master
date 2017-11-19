package com.example.tommylee.myapplication;

/**
 * Created by tommylee on 1/11/2017.
 */

public class DataFetch {
    private int id;
    private String description,image_link;
    public DataFetch(String description,String image_link){
        this.description=description;
        //image_link=address
        this.image_link=image_link;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getImage_link(){
        return image_link;
    }
    public void setImage_link(String image_link){
        this.image_link=image_link;
    }
}
