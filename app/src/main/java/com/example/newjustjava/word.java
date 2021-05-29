package com.example.newjustjava;
//This class contains the english as well as hindi translation of the restaurants.
public class word {
    private String eng;
    private String hin;
    private static final int NO_IMAGE=-1;
    private int image_id=NO_IMAGE;
    private int audio;
    public word(String ceng,String chin)
    {
        eng=ceng;
        hin=chin;
    }
    public word(String ceng,String chin,int idOfImage,int resId)
    {
        eng=ceng;
        hin=chin;
        image_id=idOfImage;
        audio=resId;
    }
    public boolean hasImage()
    {
        return image_id!=NO_IMAGE;
    }
    public String getenglishword()
    {
        return eng;
    }
    public String gethindiword()
    {
        return hin;
    }
    public int getImage_id()
    {
        return image_id;
    }
    public int getaudioid()
    {
        return audio;
    }
}
