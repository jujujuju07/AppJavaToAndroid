
package com.example.testappjavatoandroid.methode.model;

import java.util.ArrayList;
import java.util.List;
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
