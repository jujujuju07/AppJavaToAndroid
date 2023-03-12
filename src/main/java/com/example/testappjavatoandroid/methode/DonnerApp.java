
package com.example.testappjavatoandroid.methode;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DonnerApp {

    @SerializedName("largeur")
    private String mLargeur;
    @SerializedName("longeur")
    private String mLongeur;

    public String getLargeur() {
        return mLargeur;
    }

    public void setLargeur(String largeur) {
        mLargeur = largeur;
    }

    public String getLongeur() {
        return mLongeur;
    }

    public void setLongeur(String longeur) {
        mLongeur = longeur;
    }

}
