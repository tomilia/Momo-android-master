package com.example.tommylee.myapplication;

/**
 * Created by tommylee on 1/11/2017.
 */

public class DataFetch {
    private String id;
    private int averageprice;
    private String description,image_link;
    public DataFetch(String description,String image_link,int averageprice,String... id){
        this.id=id[0];
        this.description=description;
        //image_link=address
        this.image_link=image_link;
        this.averageprice=averageprice;
    }
    public String getId(){
        return id;
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
    public int getAveragePrice(){
        return averageprice;
    }
}
