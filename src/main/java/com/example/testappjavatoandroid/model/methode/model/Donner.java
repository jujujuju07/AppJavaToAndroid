
package com.example.testappjavatoandroid.model.methode.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Donner {

    @SerializedName("image")
    private String mImage;
    @SerializedName("text")
    private String mText;

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

}
