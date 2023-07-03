
package com.example.testappjavatoandroid.model.methode.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Response {

    @SerializedName("Donner")
    private List<Donner> mDonner;
    @SerializedName("line")
    private String mLine;

    public List<Donner> getDonner() {
        return mDonner;
    }

    public void setDonner(List<Donner> donner) {
        mDonner = donner;
    }

    public String getLine() {
        return mLine;
    }

    public void setLine(String line) {
        mLine = line;
    }

}
