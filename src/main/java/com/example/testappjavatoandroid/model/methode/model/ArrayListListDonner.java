
package com.example.testappjavatoandroid.model.methode.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ArrayListListDonner {

    @SerializedName("arrayListDonner")
    private ArrayList<ArrayList<Donner>> mArrayListDonner;

    public ArrayList<ArrayList<Donner>> getArrayListDonner() {
        return mArrayListDonner;
    }

    public void setArrayListDonner(ArrayList<ArrayList<Donner>> arrayListDonner) {
        mArrayListDonner = arrayListDonner;
    }

}
